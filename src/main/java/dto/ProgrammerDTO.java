package dto;

import com.fasterxml.jackson.annotation.JsonValue;
import dao.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
/**
 * Clase POJO de transporte de datos para programmer. Implementa
 * etiquetas JSON para su serialización
 */
public class ProgrammerDTO {
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
    /**
     * metodo toString
     * @return String representando al objeto
     */
    @Override
    @JsonValue
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", entryDate=" + entryDate +
                ", department=" + department +
                ", salary=" + salary +
                ", password='" + password + '\'' +
                ", technologies=" + technologies +
                ", activeProjects=" + activeProjects +
                ", commits=" + commits +
                ", issues=" + issues +
                ", logins=" + logins +
                '}';
    }
    /**
     * metodo equals
     * @param o objeto a comparar
     * @return true si la id coincide, false si no
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammerDTO that = (ProgrammerDTO) o;
        return Objects.equals(id, that.id);
    }

}
