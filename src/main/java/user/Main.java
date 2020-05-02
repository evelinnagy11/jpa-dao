package user;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.google.inject.Guice;
import com.google.inject.Injector;

import guice.PersistenceModule;
import user.model.User;

public class Main {

    private static Faker faker = new Faker(new Locale("en"));

    private static User createUser() {
        User user = User.builder()
                .username(faker.name().username())
                .password(faker.internet().password())
                .name(faker.name().name())
                .email(faker.internet().emailAddress())
                .build();
        return user;
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        UserDao userDao = injector.getInstance(UserDao.class);
        for (int i = 0; i < 10; i++) {
            User user = createUser();
            userDao.persist(user);
        }
        userDao.findAll()
                .stream()
                .forEach(System.out::println);
    }

}
