package dao;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
public class Issue {
    private UUID id;
    private String title;
    private String text;
    private LocalDateTime date;
    private Set<Programmer> programmers;
    private
}
