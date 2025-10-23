package com.example.onlinequizapp.repository;

import com.example.onlinequizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
