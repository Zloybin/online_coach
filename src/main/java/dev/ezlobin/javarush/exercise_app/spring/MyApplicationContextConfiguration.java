package dev.ezlobin.javarush.exercise_app.spring;

import dev.ezlobin.javarush.exercise_app.model.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan
public class MyApplicationContextConfiguration {

    @Bean
    @Scope("singleton")
    public SessionFactory sessionFactory(){
        return new org.hibernate.cfg.Configuration()
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
