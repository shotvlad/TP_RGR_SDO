package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_students")
public class Student implements java.io.Serializable {
    private Integer studentNum;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String email;
    private Date statusDate;
    private List<Course> course;
    private List<StudentTask> studentTask;

    public Student() {

    }

    public Student(String surname, String name, String patronymic, String phone, String email, Date statusDate) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.email = email;
        this.statusDate = statusDate;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "StudentNum", unique = true, nullable = false)
    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    @Column(name = "Surname", nullable = false, length = 30)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "Name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Patronymic", nullable = false, length = 30)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Column(name = "Phone", nullable = false, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "Email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate", length = 10)
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    @ManyToMany
    @JoinTable (name="t_students_courses",
            joinColumns=@JoinColumn (name="student_StudentNum"),
            inverseJoinColumns=@JoinColumn(name="course_CourseNum"))
    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    public List<StudentTask> getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(List<StudentTask> studentTask) {
        this.studentTask = studentTask;
    }

    @Override
    public String toString(){
        return "ФИО: " + this.getSurname()+ " " + this.getName() + " " + this.getPatronymic() + "\n" +
                "Номер ID: " + this.getStudentNum();
    }
}
