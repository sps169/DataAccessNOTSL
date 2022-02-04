package dao;

import dto.ProgrammerDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Programmer.findAll", query = "SELECT l FROM Programmer l")
})
@Entity
public class Programmer {
    private String id;
    private String name;
    private String mail;
    private Date entryDate;
    private Department department;
    private double salary;
    private String password;
    private Set<Technology> technologies;
    private Set<Project> activeProjects;
    private Set<Commit> commits;
    private Set<Issue> issues;
    private Set<Login> logins;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entry_date) {
        this.entryDate = entry_date;
    }

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    public Set<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Project> getActiveProjects() {
        return activeProjects;
    }

    public void setActiveProjects(Set<Project> activeProjects) {
        this.activeProjects = activeProjects;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "programmer", cascade = CascadeType.DETACH)
    public Set<Commit> getCommits() {
        return commits;
    }

    public void setCommits(Set<Commit> commits) {
        this.commits = commits;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }
    @OneToMany(fetch = FetchType.EAGER)
    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", mail:'" + mail + '\'' +
                ", entryDate:" + entryDate +
                ", salary:" + salary +
                '}';
    }

    public String fullToString() {
        return "Programmer{" +
                "id:'" + id + '\'' +
                ", name:'" + name + '\'' +
                ", mail:'" + mail + '\'' +
                ", entryDate:" + entryDate +
                ", department:" + department +
                ", salary:" + salary +
                ", password:'" + password + '\'' +
                ", technologies:" + technologies +
                ", activeProjects:" + activeProjects +
                ", commits:" + commits +
                ", issues:" + issues +
                ", logins:" + logins +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return Objects.equals(id, that.id);
    }
}
