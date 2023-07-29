package dev.ezlobin.javarush.exercise_app.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sets")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Set {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "set_number")
    private Integer setNumber;

    @Column(name = "weights")
    private Integer weight;

    @Column
    private Integer repeats;
}
