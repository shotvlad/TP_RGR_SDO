package entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_tasks")
public class Task implements java.io.Serializable {
    private Integer idTask;
    private String infoTask;
    private Theory theory;
    private List<StudentTask> studentTask;
    private Date statusDate;

    public Task() {

    }

    public Task(Theory theory, String infoTask, Date statusDate) {
        this.theory = theory;
        this.infoTask = infoTask;
        this.statusDate = statusDate;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "IdTask", unique = true, nullable = false)
    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    @Column(name = "InfoTask", nullable = false, length = 500)
    public String getInfoTask() {
        return infoTask;
    }

    public void setInfoTask(String infoTask) {
        this.infoTask = infoTask;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "IdTask")
    public Theory getTheory() {
        return theory;
    }

    public void setTheory(Theory theory) {
        this.theory = theory;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "task")
    public List<StudentTask> getStudentTask() {
        return studentTask;
    }

    public void setStudentTask(List<StudentTask> studentTask) {
        this.studentTask = studentTask;
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
