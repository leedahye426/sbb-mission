package com.ll.sbbmission.global.initData;

import com.ll.sbbmission.answer.AnswerService;
import com.ll.sbbmission.question.Question;
import com.ll.sbbmission.question.QuestionService;
import com.ll.sbbmission.user.SiteUser;
import com.ll.sbbmission.user.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {

    @Bean
    public ApplicationRunner initNotProd(QuestionService questionService, UserService userService, AnswerService answerService) {
        return args -> {
            SiteUser user1 = userService.create("user", "test12@test.com", "1234");
            SiteUser user2 = userService.create("user2", "23@test.com", "1234");

            for(int i = 1; i <= 300; i++) {
                String subject = "%d번 질문 데이터 입니다.".formatted(i);
                String content = "%d번 내용 데이터 입니다. ".formatted(i);
                Question question = questionService.create(subject, content, user1);
                answerService.create(question, "답변입니다.", user2);
            }

        };
    }
}
