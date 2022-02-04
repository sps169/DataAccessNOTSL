package dao;

import dto.LoginDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
@Entity
@NamedQueries({
        @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
})
public class Login {
    private String id;
    private Programmer programmer;
    private Date time;
    private UUID token;
    private boolean active;

    public Login(String id, Programmer programmer, Date time, UUID token, boolean active) {
        this.id = id;
        this.programmer = programmer;
        this.time = time;
        this.token = token;
        this.active = active;
    }

    public Login() {

    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "{" +
                "id:'" + id + '\'' +
                ", time:" + time +
                ", token:" + token +
                ", active:" + active +
                '}';
    }


    public String fullToString() {
        return "Login{" +
                "id:'" + id + '\'' +
                ", programmer:" + programmer +
                ", time:" + time +
                ", token:" + token +
                ", active:" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return Objects.equals(id, login.id);
    }
}
