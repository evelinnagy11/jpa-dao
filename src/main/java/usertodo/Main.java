package usertodo;

import java.time.LocalDate;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;
import user.model.User;
import user.UserDao;
import usertodo.model.UserTodo;

public class Main {

    private static Faker faker = new Faker(new Locale("en"));

    private static User createUser(String username) {
        User user = User.builder()
                .username(username)
                .password(faker.internet().password())
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .build();
        return user;
    }

    private static UserTodo createTodo(User user) {
        UserTodo userTodo = UserTodo.builder()
                .user(user)
                .description(faker.lorem().sentence(3))
                .priority(faker.options().option(UserTodo.Priority.class))
                .done(faker.bool().bool())
                .dueDate(LocalDate.now().plusDays(faker.number().numberBetween(1, 10)))
                .build();
        return userTodo;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        UserDao userDao = injector.getInstance(UserDao.class);
        UserTodoDao userTodoDao = injector.getInstance(UserTodoDao.class);

        for (int i = 0; i < 5; i++) {
            User user = createUser("user" + i);
            userDao.persist(user); // Persisting the user
            System.out.println(user);
            for (int j = 0; j < 5; j++) {
                UserTodo userTodo = createTodo(user);
                System.out.println(userTodo);
                userTodoDao.persist(userTodo); // Persisting the task
            }
        }
        User user = userDao.findByUsername("user4").get();
        System.out.printf("All tasks of user '%s':%n", user.getUsername());
        userTodoDao.findAll(user)
                .stream()
                .forEach(System.out::println);

        System.out.printf("Unfinished tasks of user '%s':%n", user.getUsername());
        userTodoDao.findUnfinished(user)
                .stream()
                .forEach(System.out::println);

        System.out.printf("High priority tasks of user '%s':%n", user.getUsername());
        userTodoDao.findByPriority(user, UserTodo.Priority.HIGH)
                .stream()
                .forEach(System.out::println);

        UserTodo userTodo = userTodoDao.findAll(user).get(0);
        userTodoDao.remove(userTodo); // Removing the first task of the user

        userTodoDao.findAll(user).stream().forEach(t -> userTodoDao.remove(t)); // Removing all remaining tasks of the user (required for being able to remove the user)
        userDao.remove(user); // Removing the user
    }

}
