package dev.ezlobin.javarush.exercise_app.spring.provider_config;

import org.hibernate.SessionFactory;

public interface SessionFactoryProvider {
    SessionFactory getSessionFactory();
}
