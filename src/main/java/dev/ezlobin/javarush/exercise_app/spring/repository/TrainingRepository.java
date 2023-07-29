package dev.ezlobin.javarush.exercise_app.spring.repository;

import dev.ezlobin.javarush.exercise_app.model.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TrainingRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public TrainingRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Training add(Training training){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(training);
            transaction.commit();
        }
        return training;
    }

    public Training remove(Training training){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(training);
            transaction.commit();
        }
        return training;
    }

    public Training getById(Long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Training.class, id);
        }
    }

    public List<Training> getAll(){
        try (Session session = sessionFactory.openSession()){
            Query<Training> query = session.createQuery("from Training", Training.class);
            return query.list();
        }
    }
}
