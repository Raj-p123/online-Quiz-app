package com.example.onlinequizapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String questionText;

    // JSON string for options (e.g. ["A","B","C","D"])
    @Column(columnDefinition = "TEXT")
    private String optionsJson;

    private String correctAnswer;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
