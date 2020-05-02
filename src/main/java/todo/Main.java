package todo;

import java.time.LocalDate;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;
import todo.model.Todo;

public class Main {

    public static void main(String[] args) 	{
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        TodoDao todoDao = injector.getInstance(TodoDao.class);

        Todo todo1 = Todo.builder()
                .description("Buy beer")
                .priority(Todo.Priority.NORMAL)
                .tags(Lists.newArrayList("shopping"))
                .build();
        System.out.println(todo1);

        Todo todo2 = Todo.builder()
                .description("Do homework")
                .priority(Todo.Priority.HIGH)
                .dueDate(LocalDate.now().plusWeeks(1))
                .tags(Lists.newArrayList("school", "programming"))
                .build();
        System.out.println(todo2);

        todoDao.persist(todo1);
        todoDao.persist(todo2);
        System.out.println(todo1);
        System.out.println(todo2);

        System.out.println("Unfinished tasks: " + todoDao.findUnfinished());
        System.out.println("High priority tasks: " + todoDao.findByPriority(Todo.Priority.HIGH));

        todoDao.remove(todo2);
        System.out.println("All tasks: " + todoDao.findAll());

        todo1.setDone(true);
        todoDao.update(todo1);
        System.out.println("All tasks: " + todoDao.findAll());
    }

}
