package runner;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

import java.util.Date;
import java.util.List;

public class Task_3 {

    public static void main(String[] args) {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //Для БД: SET GLOBAL time_zone = '+3:00';

        //3. Update. Добавить задания студентам от курсов, на которые подписаны

        List<Student> students = session.createQuery("FROM Student").list();
        List<StudentTask> studentTasks = session.createQuery("FROM StudentTask").list();

        boolean step = false;

        for (Student student : students) {
            if (student.getCourse().size() != 0) {
                for (Course course : student.getCourse()) {
                    for (Theory theory : course.getTheories()) {
                        for (StudentTask studentTask : studentTasks) {
                            if (studentTask.getStudent() == student && studentTask.getTask() == theory.getTask()) {
                                step = true;
                                break;
                            }
                        }

                        if (!step) {
                            StudentTask studentTask = new StudentTask(student, theory.getTask(), "Не выполнено", new Date());
                            session.save(studentTask);
                        }

                        step = false;
                    }
                }
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
