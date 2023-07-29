package dev.ezlobin.javarush.exercise_app.spring.repository;

import dev.ezlobin.javarush.exercise_app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class UserRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User add(User user){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        return user;
    }

    public User remove(User user){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
        return user;
    }

    public User getById(Long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(User.class, id);
        }
    }

    public Optional<User> getByLogin(String login){
        try (Session session = sessionFactory.openSession()){
            Query<User> query = session.createQuery("from User u where u.login = :login", User.class);
            query.setParameter("login", login);
            return query.uniqueResultOptional();
        }
    }

    public List<User> getAll(){
        try(Session session = sessionFactory.openSession()){
            Query<User> allExercisesQuery = session.createQuery("from User ", User.class);
            return allExercisesQuery.list();
        }
    }
}
