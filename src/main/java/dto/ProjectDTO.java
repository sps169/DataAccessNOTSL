package dto;

import dao.Programmer;
import dao.Repository;

import java.time.LocalDateTime;
import java.util.Objects;

public class ProjectDTO {
    private String id;
    private String name;
    private double budget;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    //    private Set<Technology> technologies;
    private Repository repository;
    private Programmer boss;

    public ProjectDTO(String id, String name, double budget, LocalDateTime startDate, LocalDateTime endDate, Repository repository, Programmer boss) {
        this.id = id;
        this.name = name;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.repository = repository;
        this.boss = boss;
    }

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

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Programmer getBoss() {
        return boss;
    }

    public void setBoss(Programmer boss) {
        this.boss = boss;
    }
    public String basicToString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", repository=" + repository.basicToString() +
                ", boss=" + boss.basicToString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTO that = (ProjectDTO) o;
        return Double.compare(that.budget, budget) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate)
                && Objects.equals(repository, that.repository)
                && Objects.equals(boss, that.boss);
    }

}
