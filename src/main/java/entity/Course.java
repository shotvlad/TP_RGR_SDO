package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_courses")
public class Course implements java.io.Serializable {
    private Integer courseNum;
    private String name;
    private String info;
    private double price;
    private Date statusDate;
    private List<Student> students;
    private List<Theory> theories;

    public Course() {

    }

    public Course(String name, String info, double price, Date statusDate) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.statusDate = statusDate;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CourseNum", unique = true, nullable = false)
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Column(name = "Name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Info", nullable = false, length = 500)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Column(name = "Price", nullable = false, length = 10)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    @JoinTable(name = "t_students_courses",
    joinColumns = @JoinColumn(name = "course_CourseNum"),
    inverseJoinColumns = @JoinColumn(name = "student_StudentNum"))
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "course")
    public List<Theory> getTheories() {
        return theories;
    }

    public void setTheories(List<Theory> theories) {
        this.theories = theories;
    }
}
