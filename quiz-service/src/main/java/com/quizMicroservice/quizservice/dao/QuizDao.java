package com.quizMicroservice.quizservice.dao;


import com.quizMicroservice.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
