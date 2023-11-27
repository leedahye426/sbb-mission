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

    public Optional<Question> getQuestion(Integer id) {
        return questionRepository.findById(id);
    }

    public void save(Question question) {
        questionRepository.save(question);
    }
}
