package com.example.onlinequizapp.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long quizId;
    private Integer score;
    private LocalDateTime takenAt;
}
