package dev.ezlobin.javarush.exercise_app.model;

import dev.ezlobin.javarush.exercise_app.enums.Equipment;
import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Set;

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


    @ElementCollection(targetClass = Equipment.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "exercise_equipments", joinColumns = @JoinColumn(name = "exercise_id", referencedColumnName = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Equipment> equipments;


}
