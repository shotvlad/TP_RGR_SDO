package runner;

import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

import java.util.List;

public class Task_2 {

    public Task_2() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //2. Вывести список подписок на курсы каждого студента и прибыль от него

        List<Student> students = session.createQuery("FROM Student").list();

        double expenses = 0;

        for (Student student : students) {
            System.out.println(student);
            if (student.getCourse().size() == 0) {
                System.out.println("Не имеет подписок на курсы. Прибыли нет.");
            } else {
                System.out.println("Подписан на курсы: ");
                for(Course course : student.getCourse()) {
                    System.out.println(course.getName() + "\n" +
                            "Стоимость: " + course.getPrice() + " BLR.");

                    expenses += course.getPrice();
                }

                System.out.println("Прибыль: " + expenses + " BLR.");

                expenses = 0;
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
