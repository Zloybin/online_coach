package dev.ezlobin.javarush.exercise_app;

import dev.ezlobin.javarush.exercise_app.dto.UserDTO;
import dev.ezlobin.javarush.exercise_app.model.User;

public class UserMapper {

    public UserDTO createUserDTO(User user){
        return UserDTO.builder()
                .login(user.getLogin())
                .build();
    }
}
