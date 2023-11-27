package com.ll.sbbmission.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("entity not found");
        }
    }

    public void save(Question question) {
        questionRepository.save(question);
    }
}
