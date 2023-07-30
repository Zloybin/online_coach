package dev.ezlobin.javarush.exercise_app;


import dev.ezlobin.javarush.exercise_app.enums.Equipment;
import dev.ezlobin.javarush.exercise_app.enums.Muscle;
import dev.ezlobin.javarush.exercise_app.model.Exercise;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.SessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.ExerciseRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        /*ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyApplicationContextConfiguration.class);
        ExerciseRepository exerciseRepository = applicationContext.getBean(ExerciseRepository.class);
        for (Exercise exercise : exerciseRepository.getAll()) {
            System.out.println(exercise);
        }*/

        /*SessionFactoryProvider sessionFactoryProvider = new XMLPropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        ExerciseRepository exerciseRepository = new ExerciseRepository(sessionFactory);
        for (Exercise exercise : exerciseRepository.getAll()) {
            System.out.println(exercise);
        }*/

        //

        /*HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:7777/exercise_app/exercise?request=2"))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/

        /*String url = "jdbc:postgresql://localhost:5432/test_db";
        String user = "ezlobin";
        String password = "Calambur26312!";


            try (Connection connection = DriverManager.getConnection(url, user, password)){
                Statement query = connection.createStatement();
                ResultSet resultSet = query.executeQuery("SELECT * FROM users;");
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    System.out.println(id);
                }
            }*/
        SessionFactoryProvider sessionFactoryProvider = new XMLPropertiesSessionFactoryProvider();
        SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();
        ExerciseRepository exerciseRepository = new ExerciseRepository(sessionFactory);
        Exercise exercise = exerciseRepository.getById(4L);
        System.out.println(exercise.toString());

        /*try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Exercise exercise = session.get(Exercise.class, 4L);
            exercise.getEquipments().add(Equipment.DUMBBELL);
            exercise.getEquipments().add(Equipment.BENCH);
            exercise.getTargetMuscles().add(Muscle.UPPER_CHEST);
            exercise.getAntagonistMuscles().add(Muscle.FRONT_DELTS);
            transaction.commit();
        }*/


    }
}
