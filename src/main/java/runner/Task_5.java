package runner;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Task_5 {

    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //Для БД: SET GLOBAL time_zone = '+3:00';

        //5. Вывести не выполненые задания и студента, кому оно принадлежит

        List<Course> courses = session.createQuery("FROM Course").list();
        List<StudentTask> studentTasks = session.createQuery("FROM StudentTask").list();

        for (Course course : courses) {
            System.out.println(course + "\n" +
                    "ID курса: " + course.getCourseNum());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите ID курса: ");
        int enter = scanner.nextInt();

        for (StudentTask studentTask : studentTasks) {
            if (studentTask.getTask().getTheory().getCourse().getCourseNum() == enter && Objects.equals(studentTask.getStatus(), "Не выполнено")) {
                System.out.println("По " + studentTask.getTask().getTheory().getName() + "\n" +
                        "Не выполнено: " + studentTask.getTask().getInfoTask() + "\n" +
                        "Студент: " + studentTask.getStudent());
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
