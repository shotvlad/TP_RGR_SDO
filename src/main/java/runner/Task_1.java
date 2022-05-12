package runner;

import entity.Course;
import entity.Student;
import entity.Theory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Task_1 {

    public Task_1() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //Для БД: SET GLOBAL time_zone = '+3:00';

        //1. Вывести список студентов, выбрать одного и вывести его задания из курсов

        List<Student> students = session.createQuery("FROM Student").list();

        for (Student student : students)
        {
            System.out.println(student);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите ID студента: ");
        int enter = scanner.nextInt();

        Student student = students.get(enter - 1);
        System.out.println(student);

        if (student.getCourse().size() != 0) {
            for (Course course : student.getCourse()) {
                System.out.println("Записан на курс " + course.getName() + "\n" +
                        "Имеет задания:");
                for (Theory theory : course.getTheories()) {
                    System.out.println(theory.getTask().getInfoTask());
                }
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
