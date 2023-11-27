package com.ll.sbbmission;

import com.ll.sbbmission.question.Question;
import com.ll.sbbmission.question.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    public void setup() {
        questionRepository.deleteAll();

        Question q1 = new Question();
        q1.setSubject("제목1");
        q1.setContent("내용1");
        q1.setCreateDate(LocalDateTime.now());

        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("제목2");
        q2.setContent("내용2");
        q2.setCreateDate(LocalDateTime.now());

        questionRepository.save(q2);
    }

    @Test
    @DisplayName("findById")
    public void t1() {
        List<Question> questionList = questionRepository.findAll();

        Optional<Question> oq = questionRepository.findById(7);

        Assertions.assertThat(oq.get().getSubject()).isEqualTo("제목1");

    }

    @Test
    @DisplayName("findAll")
    public void t2() {
        List<Question> questionList = questionRepository.findAll();

        Assertions.assertThat(questionList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("findBySubject")
    public void t3() {
        Question q = questionRepository.findBySubject("제목1");

        Assertions.assertThat(q.getContent()).isEqualTo("내용1");
    }

    @Test
    @DisplayName("findBySubjectAndContent")
    public void t4() {
        Question q = questionRepository.findBySubjectAndContent("제목1", "내용1");

        Assertions.assertThat(q.getSubject()).isEqualTo("제목1");
        Assertions.assertThat(q.getContent()).isEqualTo("내용1");
    }

    @Test
    @DisplayName("findBySubjectLike")
    public void f5() {
        List<Question> questions = questionRepository.findBySubjectLike("%제목%");

        Assertions.assertThat(questions.get(0).getSubject()).isEqualTo("제목1");
        Assertions.assertThat(questions.get(1).getSubject()).isEqualTo("제목2");

        Assertions.assertThat(questions.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("수정")
    public void f6() {
        Question q = questionRepository.findBySubject("제목1");

        q.setSubject("수정된 제목");

        questionRepository.save(q);

        Question modified_q = questionRepository.findBySubject("수정된 제목");
        Assertions.assertThat(modified_q.getSubject()).isEqualTo("수정된 제목");
    }

    @Test
    @DisplayName("삭제")
    public void f7() {
        List<Question> questions = questionRepository.findAll();
        Assertions.assertThat(questions.size()).isEqualTo(2);

        Optional<Question> optional_Q = Optional.ofNullable(questions.get(0));
        if(optional_Q.isPresent()) {
            Question q = optional_Q.get();

            questionRepository.delete(q);
        }

        Assertions.assertThat(questionRepository.count()).isEqualTo(1);
    }
}
