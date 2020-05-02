package todo;

import java.util.List;

import com.google.inject.persist.Transactional;

import jpa.GenericJpaDao;
import todo.model.Todo;

public class TodoDao extends GenericJpaDao<Todo> {

    @Transactional
    public List<Todo> findUnfinished() {
        return entityManager.createQuery("SELECT t FROM Todo t WHERE t.done = false ORDER BY t.created", Todo.class).getResultList();
    }

    @Transactional
    public List<Todo> findByPriority(Todo.Priority priority) {
        return entityManager.createQuery("SELECT t FROM Todo t WHERE t.priority = :priority ORDER BY t.created", Todo.class)
                .setParameter("priority", priority)
                .getResultList();
    }

}
