package com.neighborhood.global.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Util {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 파일이름이 포함된 s3 경로를 생성하고, s3 스토리지에 파일 하나를 저장하는 putS3()를 호출한다.
     * @param multipartFile: s3에 저장할 파일 객체
     * @param dirName: 파일이 저장될 s3 폴더 경로
     * @param fileName: s3에 저장될 파일 이름. 보통 uuid 사용
     * @return 저장된 파일의 url
     */
    public String upload(MultipartFile multipartFile, String dirName, String fileName) {
        String s3Path = dirName + "/" + fileName;
        try {
            return putS3(multipartFile, s3Path);
        } catch (IOException e) {
            throw new AmazonS3Exception("S3 업로드 실패");
        }

    }

    /**
     * 지정한 s3 경로에 있는 파일을 삭제한다.
     * @param s3Path 파일이름이 포함된 s3 경로
     */
    public void delete(String s3Path) {
        amazonS3Client.deleteObject(bucket, s3Path);
        log.info("S3에서 삭제된 파일: {}", s3Path);
    }


    /**
     * 지정된 s3 경로에 파일 하나를 저장한다.
     * @param multipartFile: 저장할 파일 객체
     * @param s3Path: 파일이 저장될 s3 경로
     * @return 저장한 파일의 url
     * @throws IOException
     */
    private String putS3(MultipartFile multipartFile, String s3Path) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        //S3 API 메소드인 putObject를 이용하여 파일 Stream을 열어서 S3에 파일을 업로드
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3Path, multipartFile.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        //getUrl 메소드를 통해서 S3에 업로드된 파일 URL을 가져오는 방식
        return amazonS3Client.getUrl(bucket, s3Path).toString();
    }
}