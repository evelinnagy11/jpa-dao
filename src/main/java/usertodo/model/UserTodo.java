package usertodo.model;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;

import todo.model.Todo;
import user.model.User;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class UserTodo extends Todo {

    @ManyToOne
    @JoinColumn(nullable=false)
    private User user;

}
