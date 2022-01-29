package dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy="uuid2")
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
        return "Login{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", token=" + token +
                ", active=" + active +
//                ", programmer=" +programmer.basicToString()+
                '}';
    }
}
