package dev.ezlobin.javarush.exercise_app.service;

import dev.ezlobin.javarush.exercise_app.model.Task;
import dev.ezlobin.javarush.exercise_app.model.Training;

public class TrainingService {

    private Training training;

    public TrainingService(Training training) {
        this.training = training;
    }

    public boolean addTask(Task task){
        return  training.getTasks().add(task);
    }
}
