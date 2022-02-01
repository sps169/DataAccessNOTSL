package dto;

import dao.*;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
public class ProgrammerDTO {
    private String id;
    private String name;
    private String mail;
    private Date entryDate;
//    private Department department;
    private double salary;
    private String password;
    //    private Set<Technology> technologies;
//    private Set<Project> activeProjects;
//    private Set<Commit> commits;
//    private Set<Issue> issues;
    private Set<Login> logins;

//    public ProgrammerDTO(String id, String name, String mail, Date entryDate, Department department,
//                         double salary, String password, Set<Project> activeProjects, Set<Commit> commits,
//                         Set<Issue> issues, Set<Login> logins) {
//        this.id = id;
//        this.name = name;
//        this.mail = mail;
//        this.entryDate = entryDate;
//        this.department = department;
//        this.salary = salary;
//        this.password = password;
//        this.activeProjects = activeProjects;
//        this.commits = commits;
//        this.issues = issues;
//        this.logins = logins;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

//    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }

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

//    public Set<Project> getActiveProjects() {
//        return activeProjects;
//    }
//
//    public void setActiveProjects(Set<Project> activeProjects) {
//        this.activeProjects = activeProjects;
//    }
//
//    public Set<Commit> getCommits() {
//        return commits;
//    }
//
//    public void setCommits(Set<Commit> commits) {
//        this.commits = commits;
//    }
//
//    public Set<Issue> getIssues() {
//        return issues;
//    }
//
//    public void setIssues(Set<Issue> issues) {
//        this.issues = issues;
//    }

    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

    public String basicToString() {
        return "ProgrammerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", entryDate=" + entryDate +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammerDTO that = (ProgrammerDTO) o;
        return Double.compare(that.salary, salary) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(mail, that.mail)
                && Objects.equals(entryDate, that.entryDate)
//                && Objects.equals(department, that.department)
                && Objects.equals(password, that.password)
//                && Objects.equals(activeProjects, that.activeProjects)
//                && Objects.equals(commits, that.commits)
//                && Objects.equals(issues, that.issues)
                && Objects.equals(logins, that.logins)
                ;
    }

}
