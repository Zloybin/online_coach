package dev.ezlobin.javarush.exercise_app.service;

import dev.ezlobin.javarush.exercise_app.model.Set;
import dev.ezlobin.javarush.exercise_app.model.Task;
import dev.ezlobin.javarush.exercise_app.model.TaskTable;

public class TaskTableService {
    private static final Integer DEFAULT_VALUE = 0;
    private Integer setCounter = DEFAULT_VALUE;
    private TaskTable taskTable;

    public TaskTableService(TaskTable taskTable) {
        this.taskTable = taskTable;
    }

    public boolean addSet(Set set){
        return taskTable.getSetList().add(set);
    }

    public boolean addSets(Set ...sets){
        for (Set set : sets) {
            set.setSetNumber(++setCounter);
            taskTable.getSetList().add(set);
        }
        return false;
    }

}
