package dev.ezlobin.javarush.exercise_app.spring.repository;

import dev.ezlobin.javarush.exercise_app.model.TaskTable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTableRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public TaskTableRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public TaskTable add(TaskTable taskTable){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(taskTable);
            transaction.commit();
        }
        return taskTable;
    }

    public TaskTable remove(TaskTable taskTable){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(taskTable);
            transaction.commit();
        }
        return taskTable;
    }

    public TaskTable getById(Long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(TaskTable.class, id);
        }
    }
}
