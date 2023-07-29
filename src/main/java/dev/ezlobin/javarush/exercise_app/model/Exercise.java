package dev.ezlobin.javarush.exercise_app.model;

import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "exercises")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor @Builder
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;

}
