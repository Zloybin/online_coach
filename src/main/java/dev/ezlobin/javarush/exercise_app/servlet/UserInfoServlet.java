package dev.ezlobin.javarush.exercise_app.servlet;

import com.google.gson.Gson;
import dev.ezlobin.javarush.exercise_app.UserMapper;
import dev.ezlobin.javarush.exercise_app.dto.UserDTO;
import dev.ezlobin.javarush.exercise_app.model.User;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user_info")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId =  (Long) session.getAttribute("userId");
        UserRepository userRepository = new UserRepository(new XMLPropertiesSessionFactoryProvider().getSessionFactory());
        User user = userRepository.getById(userId);
        UserMapper userMapper = new UserMapper();
        UserDTO userDTO = userMapper.createUserDTO(user);

        String json = new Gson().toJson(userDTO);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()){
            writer.println(json);
            writer.flush();
        }
    }
}
