package com.neighborhood.domain.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.neighborhood.domain.member.entity.Member;
import com.neighborhood.domain.member.repository.MemberRepository;
import com.neighborhood.global.exception.RestApiException;
import com.neighborhood.global.exception.errorCode.CommonErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FCMService {
    private  final FirebaseMessaging firebaseMessaging;
    private  final MemberRepository memberRepository;

    public String sendNotification(FCMNotificationRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getTargetId())
                .orElseThrow(() -> new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND));

        if(member.getFcmToken()!=null){
            Notification notification = Notification.builder().setTitle("기분 최신화 요청")
                    .setBody("가족이 "+member.getName()+"님의 현재 기분을 궁금해해요!").build();
            Message message = Message.builder().setToken(member.getFcmToken())
                    .setNotification(notification).build();
            try{
                firebaseMessaging.send(message);
                return "알림을 성공적으로 전송했습니다. 회원 아이디: "+ member.getMemberId();
            } catch (FirebaseMessagingException e) {
                e.printStackTrace();
                return "알림보내기는데 실패하였습니다 회원아이디: "+ member.getMemberId();
            }
        }else{
            return "서버에 저장된 해당 유저의 토큰이 없습니다 회원아이디: "+ member.getMemberId();
        }
    }
}
