package todo.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Todo {

    public static enum Priority {
        LOW,
        NORMAL,
        HIGH
    }

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable=false)
    private LocalDateTime created;

    @Column(nullable=false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Priority priority;

    private LocalDate dueDate;

    private boolean done;

    @ElementCollection
    @Column(name="tag", nullable=false)
    private List<String> tags;

    @Version
    @Setter(AccessLevel.NONE)
    private long version;

    @PrePersist
    protected void onPersist() {
        this.created = LocalDateTime.now();
    }

}
