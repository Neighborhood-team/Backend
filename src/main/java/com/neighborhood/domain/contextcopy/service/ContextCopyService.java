package com.neighborhood.domain.contextcopy.service;

import com.neighborhood.domain.contextcopy.dto.ContextCopyDto;
import com.neighborhood.domain.contextcopy.repository.ContextCopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;


@Service
@RequiredArgsConstructor
@Transactional
public class ContextCopyService {

    private final ContextCopyRepository contextCopyRepository;
    //여기서 parent,child로 바꿔서 주기+ list 중 랜덤하게 제공

    public ContextCopyDto getRandomContext(String familyType){
        String type;
        if(familyType=="DAD"||familyType=="MOM")
            type = "PARENT";
        else type = "CHILD";

        System.out.println(type);
        List<ContextCopyDto> FamilyTypes = contextCopyRepository.findByFamily_type(type);
        Random random = new Random();
        int randomNumber = random.nextInt(9)+1;
        return FamilyTypes.get(randomNumber);
    }


}
