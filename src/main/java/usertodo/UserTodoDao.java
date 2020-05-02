package usertodo;

import java.util.List;

import com.google.inject.persist.Transactional;

import jpa.GenericJpaDao;
import user.model.User;
import usertodo.model.UserTodo;

public class UserTodoDao extends GenericJpaDao<UserTodo> {

    @Transactional
    public List<UserTodo> findAll(User user) {
        return entityManager.createQuery("SELECT t FROM UserTodo t WHERE t.user = :user ORDER BY t.created", UserTodo.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    public List<UserTodo> findAll(String username) {
        return entityManager.createQuery("SELECT t FROM UserTodo t WHERE t.user.username = :username ORDER BY t.created", UserTodo.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Transactional
    public List<UserTodo> findUnfinished(User user) {
        return entityManager.createQuery("SELECT t FROM UserTodo t WHERE t.user = :user AND t.done = false ORDER BY t.created", UserTodo.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Transactional
    public List<UserTodo> findByPriority(User user, UserTodo.Priority priority) {
        return entityManager.createQuery("SELECT t FROM UserTodo t WHERE t.user = :user AND t.priority = :priority ORDER BY t.created", UserTodo.class)
                .setParameter("user", user)
                .setParameter("priority", priority)
                .getResultList();
    }

}
