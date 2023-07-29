package dev.ezlobin.javarush.exercise_app.servlet;

import com.google.gson.Gson;
import dev.ezlobin.javarush.exercise_app.creator.ObjectCreator;
import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import dev.ezlobin.javarush.exercise_app.model.Exercise;
import dev.ezlobin.javarush.exercise_app.spring.MyApplicationContextConfiguration;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.SessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.ExerciseRepository;
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
import java.util.List;

@WebServlet("/exercise")
public class ExerciseServlet extends HttpServlet {
    private static final String SESSION_FACTORY_ATTRIBUTE_VALUE = "session_factory";
    private static final String EXERCISE_REPOSITORY_VALUE = "exercise_repository";
    private SessionFactoryProvider sessionFactoryProvider;
    private ExerciseRepository exerciseRepository;
    private ObjectCreator objectCreator = new ObjectCreator();

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        this.exerciseRepository = applicationContext.getBean(ExerciseRepository.class);
        /*sessionFactoryProvider = new XMLPropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        getServletConfig().getServletContext().setAttribute(SESSION_FACTORY_ATTRIBUTE_VALUE, sessionFactory);

        exerciseRepository = new ExerciseRepository(sessionFactory);
        getServletConfig().getServletContext().setAttribute(EXERCISE_REPOSITORY_VALUE, exerciseRepository);*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json;
        String request = req.getParameter("request");
        if (request.equals("all")) {
            List<Exercise> allExercises = null;
            allExercises = exerciseRepository.getAll();
            json = new Gson().toJson(allExercises);
        } else if(request.equals("filtered")){
            String muscleGroupValue = req.getParameter("muscle_group");
            MuscleGroup muscleGroup = MuscleGroup.valueOf(muscleGroupValue);
            List<Exercise> filteredResult = exerciseRepository.getWithFilterMuscleGroup(muscleGroup);
            json = new Gson().toJson(filteredResult);
        }else {
            Long exercise_id = Long.valueOf(request);
            Exercise exercise = exerciseRepository.getById(exercise_id);
            json = new Gson().toJson(exercise);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter writer = resp.getWriter()) {
            writer.println(json);
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        MuscleGroup muscleGroup = MuscleGroup.valueOf(req.getParameter("muscle_group"));
        Exercise newExercise = Exercise.builder()
                .title(title)
                .muscleGroup(muscleGroup)
                .build();
        newExercise = exerciseRepository.add(newExercise);
        String json = new Gson().toJson(newExercise);

        resp.setContentType("application/json");
        try (PrintWriter writer = resp.getWriter()){
            writer.println(json);
            writer.flush();
        }
    }
}
