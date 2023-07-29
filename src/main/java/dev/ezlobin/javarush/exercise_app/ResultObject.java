package dev.ezlobin.javarush.exercise_app;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
public class ResultObject {
    private String status;
    private String message;
}
