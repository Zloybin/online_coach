package dev.ezlobin.javarush.exercise_app.creator;

import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import dev.ezlobin.javarush.exercise_app.model.*;
import org.hibernate.boot.registry.selector.spi.StrategyCreator;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectCreator {

    public User createUser(String firstName, String lastName, String login, String mail, String password){
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .login(login)
                .mail(mail)
                .password(password)
                .trainings(new CopyOnWriteArrayList<>())
                .build();
    }

    public Training createTraining(String trainingsType) {
        return Training.builder()
                .trainingsType(trainingsType)
                .creationDate(LocalDate.now())
                .tasks(new CopyOnWriteArrayList<>())
                .build();
    }

    public Task createTask (Exercise exercise, TaskTable taskTable){
        return Task.builder()
                .exercise(exercise)
                .taskTable(taskTable)
                .build();
    }

    public TaskTable createTaskTable (){
        return TaskTable.builder()
                .setList(new CopyOnWriteArrayList<>()).build();
    }

    public Exercise createExercise (String title, MuscleGroup muscleGroup){
        return Exercise.builder()
                .title(title)
                .muscleGroup(muscleGroup)
                .build();
    }

    public Set createSet (Integer weight, Integer repeats ){
        return Set.builder()
                .weight(weight)
                .repeats(repeats)
                .build();
    }
}
