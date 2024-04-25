package in.ashokit.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eid;
    private String name;
    private Long phno;
    private String classmode;
    private String course;
    private String status;
    @Column(name = "c_id", insertable = false, updatable = false)
    private Integer cid;
    
    @ManyToOne
    @JoinColumn(name = "c_id")
    private Counsellor counsellor;
    
    @CreationTimestamp
    private Date createdDate;
    
    @UpdateTimestamp
    private Date updatedDate;

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhno() {
        return phno;
    }

    public void setPhno(Long phno) {
        this.phno = phno;
    }

    public String getClassmode() {
        return classmode;
    }

    public void setClassmode(String classmode) {
        this.classmode = classmode;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Counsellor getCounsellor() {
        return counsellor;
    }

    public void setCounsellor(Counsellor counsellor) {
        this.counsellor = counsellor;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Enquiry [eid=" + eid + ", name=" + name + ", phno=" + phno + ", classmode=" + classmode + ", course="
                + course + ", status=" + status + ", cid=" + cid + ", createdDate=" + createdDate + ", updatedDate="
                + updatedDate + "]";
    }
}
