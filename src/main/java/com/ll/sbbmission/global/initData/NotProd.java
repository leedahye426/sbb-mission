package com.ll.sbbmission.global.initData;

import com.ll.sbbmission.question.QuestionService;
import com.ll.sbbmission.user.SiteUser;
import com.ll.sbbmission.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(QuestionService questionService, UserService userService) {
        return args -> {
            SiteUser user1 = userService.create("user1", "1234", "test@test.com");
            for(int i = 1; i <= 300; i++) {
                String subject = "%d번 질문 데이터 입니다.".formatted(i);
                String content = "%d번 내용 데이터 입니다. ".formatted(i);
                questionService.create(subject, content, user1);
            }
        };
    }
}
