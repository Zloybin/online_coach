package dev.ezlobin.javarush.exercise_app;


import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        String url = "jdbc:postgresql://localhost:5432/test_db";
        String user = "ezlobin";
        String password = "Calambur26312!";


            try (Connection connection = DriverManager.getConnection(url, user, password)){
                Statement query = connection.createStatement();
                ResultSet resultSet = query.executeQuery("SELECT * FROM users;");
                while (resultSet.next()){
                    int id = resultSet.getInt("id");
                    System.out.println(id);
                }
            }
    }
}
