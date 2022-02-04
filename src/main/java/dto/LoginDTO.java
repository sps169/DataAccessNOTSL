package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import dao.Programmer;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class LoginDTO {
    private String id;
    private Programmer programmer;
    private Date time;
    private UUID token;
    private boolean active;

    public LoginDTO(String id, Programmer programmer, Date time, UUID token, boolean active) {
        this.id = id;
        this.programmer = programmer;
        this.time = time;
        this.token = token;
        this.active = active;
    }

    public LoginDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
    @JsonValue
    public String toString() {
        return "{" +
                "id=" + id +
                ", programmer=" +programmer+
                ", time=" + time +
                ", token=" + token +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(id, loginDTO.id);
    }
}
