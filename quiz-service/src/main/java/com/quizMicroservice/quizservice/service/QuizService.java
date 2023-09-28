package com.quizMicroservice.quizservice.service;


import com.quizMicroservice.quizservice.dao.QuizDao;
import com.quizMicroservice.quizservice.entity.QuestionWrapper;
import com.quizMicroservice.quizservice.entity.Quiz;
import com.quizMicroservice.quizservice.entity.Response;
import com.quizMicroservice.quizservice.feign.QuizInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizInterface quizInterface;
    private final QuizDao quizDao;


    public ResponseEntity<String> create(String category, int numQ, String title) {
            List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
          Quiz quiz = quizDao.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionWrapper>> questions= quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;

    }
}
