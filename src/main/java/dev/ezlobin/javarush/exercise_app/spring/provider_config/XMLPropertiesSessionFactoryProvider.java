package dev.ezlobin.javarush.exercise_app.spring.provider_config;

import dev.ezlobin.javarush.exercise_app.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

public class XMLPropertiesSessionFactoryProvider implements SessionFactoryProvider{
    @Override
    public SessionFactory getSessionFactory() {
        return new Configuration()
                .configure()
                .addAnnotatedClass(TaskTable.class)
                .addAnnotatedClass(Exercise.class)
                .addAnnotatedClass(Set.class)
                .addAnnotatedClass(Task.class)
                .addAnnotatedClass(Training.class)
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
