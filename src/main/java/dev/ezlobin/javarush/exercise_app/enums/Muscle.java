package dev.ezlobin.javarush.exercise_app.enums;

public enum Muscle {
    LATISSIMUS("Широчайшие", MuscleGroup.BACK),
    UPPER_CHEST("Верх груди", MuscleGroup.CHEST),
    MIDDLE_CHEST("Середина груди", MuscleGroup.CHEST),
    LOWER_CHEST("Низ груди", MuscleGroup.CHEST),
    FRONT_DELTS("Передние дельты", MuscleGroup.SHOULDERS),
    TRICEPS("Трицепс", MuscleGroup.ARMS);
    private String translation;
    private MuscleGroup muscleGroup;

    Muscle(String translation, MuscleGroup muscleGroup) {
        this.translation = translation;
        this.muscleGroup = muscleGroup;
    }
}
