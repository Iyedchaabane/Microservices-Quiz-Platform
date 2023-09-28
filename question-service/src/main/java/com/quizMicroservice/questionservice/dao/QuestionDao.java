package com.quizMicroservice.questionservice.dao;


import com.quizMicroservice.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "select q.id from Question q where q.category=:category order by RANDOM() LIMIT :numQ" , nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
