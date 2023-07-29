package dev.ezlobin.javarush.exercise_app.spring.repository;

import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import dev.ezlobin.javarush.exercise_app.model.Exercise;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ExerciseRepository {
    private  final SessionFactory sessionFactory;

    @Autowired
    public ExerciseRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Exercise add(Exercise exercise){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.persist(exercise);
            transaction.commit();
        }

        return exercise;
    }

    public Exercise remove(Exercise exercise){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.remove(exercise);
            transaction.commit();
        }

        return exercise;
    }

    public Exercise getById(Long id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Exercise.class, id);
        }
    }

    public List<Exercise> getAll(){
        try(Session session = sessionFactory.openSession()){
            Query<Exercise> allExercisesQuery = session.createQuery("from Exercise", Exercise.class);
            return allExercisesQuery.list();
        }
    }

    public List<Exercise> getWithFilterMuscleGroup(MuscleGroup muscleGroup){
        try (Session session = sessionFactory.openSession()){
            Query<Exercise> query = session.createQuery("from Exercise where muscleGroup = :muscleGroup", Exercise.class);
            query.setParameter("muscleGroup", muscleGroup);
            return query.list();
        }
    }
}
