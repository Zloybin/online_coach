package dev.ezlobin.javarush.exercise_app.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Training {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "trainings_type", nullable = false)
    private String trainingsType;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "trainings_id")
    private List<Task> tasks;

}
