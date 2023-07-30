package dev.ezlobin.javarush.exercise_app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum MuscleGroup {
    CHEST("Грудь"),
    BACK("Спина"),
    LEGS("Ноги"),
    SHOULDERS("Плечи"),
    ARMS("Руки"),
    ABS("Пресс"),
    CALVES("Икры");

    private final String description;

    public MuscleGroup getByDescription(String description){
        for (MuscleGroup muscleGroup : values()) {
            if (muscleGroup.getDescription().equals(description)){
                return muscleGroup;
            }else{
                throw new IllegalArgumentException("Wrong muscle group!");
            }
        }
        return null;
    }

    public List<MuscleGroup> getAll(){
        return List.of(MuscleGroup.values());
    }

}
