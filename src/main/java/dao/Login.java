package dao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Login {
    private UUID id;
    private Programmer programmer;
    private LocalDateTime time;
    private UUID token;
    private boolean active;

    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @ManyToOne
    public Programmer getIdProgrammer() {
        return programmer;
    }

    public void setIdProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
}
