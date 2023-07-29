package dev.ezlobin.javarush.exercise_app.service;

import dev.ezlobin.javarush.exercise_app.model.Training;
import dev.ezlobin.javarush.exercise_app.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.concurrent.CopyOnWriteArrayList;

public class UserService {

    private User user;

    public UserService(User user) {
        this.user = user;
    }

    public boolean addTraining(Training training){
        return user.getTrainings().add(training);
    }

}
