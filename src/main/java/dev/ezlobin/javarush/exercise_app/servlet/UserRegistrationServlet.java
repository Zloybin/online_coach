package dev.ezlobin.javarush.exercise_app.servlet;

import com.google.gson.Gson;
import dev.ezlobin.javarush.exercise_app.ResultObject;
import dev.ezlobin.javarush.exercise_app.creator.ObjectCreator;
import dev.ezlobin.javarush.exercise_app.model.User;
import dev.ezlobin.javarush.exercise_app.spring.MyApplicationContextConfiguration;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.SessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/registration")
public class UserRegistrationServlet extends HttpServlet {
    private static final String SESSION_FACTORY_ATTRIBUTE_VALUE = "session_factory";
    private static final String USER_REPOSITORY_VALUE = "user_repository";
    private static final String FIRST_NAME_PARAMETER = "firstname";
    private static final String LAST_NAME_PARAMETER = "lastname";
    private static final String LOGIN_PARAMETER = "login";
    private static final String MAIL_PARAMETER = "mail";
    private static final String PASSWORD_PARAMETER = "password";
    private SessionFactoryProvider sessionFactoryProvider;
    private UserRepository userRepository;
    private ObjectCreator objectCreator = new ObjectCreator();

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        this.userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResultObject result = ResultObject.builder()
                .status("fail")
                .build();

        String login = req.getParameter(LOGIN_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        Optional<User> userOptional = userRepository.getByLogin(login);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                req.getSession().setAttribute("userId", user.getId());
                result.setStatus("success");
                result.setMessage("Validation complete");
            }else {
                result.setMessage("Неверный пароль!");
            }
        }else{
            result.setMessage(String.format("Неверный логин пользователя!\nПользователь с логином %s отсутсвует в базе данных", login));
        }

        String json = new Gson().toJson(result);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()){
            writer.println(json);
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter(FIRST_NAME_PARAMETER);
        String lastName = req.getParameter(LAST_NAME_PARAMETER);
        String login = req.getParameter(LOGIN_PARAMETER);
        String mail = req.getParameter(MAIL_PARAMETER);
        String password = req.getParameter(PASSWORD_PARAMETER);

        User user = objectCreator.createUser(firstName, lastName, login, mail, password);
        userRepository.add(user);

        resp.setContentType("text/html; charset=utf-8");

        try (PrintWriter writer = resp.getWriter()){
            writer.println("User was created");
            writer.flush();
        }


    }

}
