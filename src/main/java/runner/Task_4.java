package runner;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.NewHibernateUtil;

import java.util.List;

public class Task_4 {

    public Task_4() {
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

        //4. Вывести курс, имеющие в информации интерисующие данные (Пример: акция, Python, не дорого)

        List<Course> courses = session.createQuery("FROM Course").list();

        String string;

        for (Course course : courses) {
            string = course.getInfo();
            if (string.contains("не дорого")) {
                System.out.println(course);
            }
        }

        session.flush();
        transaction.commit();
        session.close();
        sf.close();
    }
}
