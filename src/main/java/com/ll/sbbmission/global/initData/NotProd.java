package com.ll.sbbmission.global.initData;

import com.ll.sbbmission.question.Question;
import com.ll.sbbmission.question.QuestionService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(QuestionService questionService) {
        return args -> {
            Question question1 = new Question();
            question1.setId(1);
            question1.setSubject("스프링 부트 모델 질문입니다.");
            question1.setContent("id는 자동으로 생성되나요?");
            question1.setCreateTime(LocalDateTime.now());

            questionService.save(question1);
        };
    }
}
