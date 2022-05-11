package entity;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_theories")
public class Theory implements java.io.Serializable {
    private Integer idTheory;
    private String name;
    private String info;
    private Course course;
    private Task task;
    private Date statusDate;

    public Theory() {

    }

    public Theory(String name, String info, Course course, Date statusDate) {
        this.name = name;
        this.info = info;
        this.course = course;
        this.statusDate = statusDate;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdTheories", unique = true, nullable = false)
    public Integer getIdTheory() {
        return idTheory;
    }

    public void setIdTheory(Integer idTheory) {
        this.idTheory = idTheory;
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

    @OneToOne(mappedBy = "theory", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CourseCipher", nullable = false)
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "StatusDate", length = 10)
    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}
