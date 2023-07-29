package dev.ezlobin.javarush.exercise_app.spring.repository;

import dev.ezlobin.javarush.exercise_app.model.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SetRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public SetRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Set add(Set set){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(set);
            transaction.commit();
        }
        return set;
    }

    public Set remove(Set set){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(set);
            transaction.commit();
        }
        return set;
    }

    public Set getById(Long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Set.class, id);
        }
    }
}
