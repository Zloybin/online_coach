package dev.ezlobin.javarush.exercise_app.dev;

import dev.ezlobin.javarush.exercise_app.creator.ObjectCreator;
import dev.ezlobin.javarush.exercise_app.enums.MuscleGroup;
import dev.ezlobin.javarush.exercise_app.model.*;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.SessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.provider_config.XMLPropertiesSessionFactoryProvider;
import dev.ezlobin.javarush.exercise_app.spring.repository.ExerciseRepository;
import dev.ezlobin.javarush.exercise_app.spring.repository.TaskRepository;
import dev.ezlobin.javarush.exercise_app.spring.repository.TaskTableRepository;
import dev.ezlobin.javarush.exercise_app.service.TaskTableService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Helper {
    private static final SessionFactoryProvider sessionFactoryProvider = new XMLPropertiesSessionFactoryProvider();
    private static final SessionFactory sessionFactory = sessionFactoryProvider.getSessionFactory();

    private static final ObjectCreator objectCreator = new ObjectCreator();
    private static final ExerciseRepository exerciseRepository = new ExerciseRepository(sessionFactoryProvider.getSessionFactory());
    private static final TaskTableRepository taskTableRepository = new TaskTableRepository(sessionFactoryProvider.getSessionFactory());
    private static final TaskRepository taskRepository = new TaskRepository(sessionFactoryProvider.getSessionFactory());



    public static void addExercises() {
        Exercise exercise1 = objectCreator.createExercise("Разгибание ног сидя", MuscleGroup.LEGS);
        Exercise exercise2 = objectCreator.createExercise("Сгибание ног сидя", MuscleGroup.LEGS);
        Exercise exercise3 = objectCreator.createExercise("Вертикльная тяга широким хватом", MuscleGroup.BACK);
        Exercise exercise4 = objectCreator.createExercise("Жим гантелей на наклонной скамье", MuscleGroup.CHEST);
        Exercise exercise5 = objectCreator.createExercise("Махи гантелей стоя", MuscleGroup.SHOULDERS);
        Exercise exercise6 = objectCreator.createExercise("Подъем прямой рукояти на бицепс с нижнего блока кроссовера", MuscleGroup.ARMS);
        Exercise exercise7 = objectCreator.createExercise("Разгибания каната на трицепс с верхнего блока кроссовера", MuscleGroup.ARMS);

        exerciseRepository.add(exercise1);
        exerciseRepository.add(exercise2);
        exerciseRepository.add(exercise3);
        exerciseRepository.add(exercise4);
        exerciseRepository.add(exercise5);
        exerciseRepository.add(exercise6);
        exerciseRepository.add(exercise7);
    }

    public static void addTask(){
        Set set1 = objectCreator.createSet(40, 15);
        Set set2 = objectCreator.createSet(50, 12);
        Set set3 = objectCreator.createSet(60, 12);
        Set set4 = objectCreator.createSet(65, 12);

        TaskTable taskTable = objectCreator.createTaskTable();
        TaskTableService taskTableService = new TaskTableService(taskTable);
        taskTableService.addSets(set1, set2, set3, set4);
        Exercise exercise = exerciseRepository.getById(3L);
        taskTableRepository.add(taskTable);
        Task task = objectCreator.createTask(exercise, taskTable);
        taskRepository.add(task);
    }

    public static void addTraining(){
        Training training = objectCreator.createTraining("Фуллбади 1");
        try (Session session = sessionFactory.openSession()){
            Task task = session.get(Task.class, 2L);
            training.getTasks().add(task);
            Transaction transaction = session.beginTransaction();
            session.persist(training);
            transaction.commit();
        }
    }

    public static void addUser(){
        User user = objectCreator.createUser("Yevhen", "Zlobin", "Zloybin", "jeka_zlobin@mail.ru", "Zloybin0707");
        try (Session session = sessionFactory.openSession()){
            Training training = session.get(Training.class, 1L);
            user.getTrainings().add(training);
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
    }

    public static void addTaskToTraining(Long trainingId, Long taskId){
        try (Session session = sessionFactory.openSession()){
            Task task = session.get(Task.class, taskId);
            Training training = session.get(Training.class, trainingId);
            training.getTasks().add(task);
            Transaction transaction = session.beginTransaction();
            session.update(training);
            transaction.commit();
        }
    }
}
