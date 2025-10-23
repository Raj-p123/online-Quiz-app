package com.example.onlinequizapp.controller;

import com.example.onlinequizapp.model.Quiz;
import com.example.onlinequizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizRepository quizRepository;

    @GetMapping
    public List<Quiz> all() { return quizRepository.findAll(); }

    @PostMapping
    public Quiz create(@RequestBody Quiz q) { return quizRepository.save(q); }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> get(@PathVariable Long id) {
        return quizRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!quizRepository.existsById(id)) return ResponseEntity.notFound().build();
        quizRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
