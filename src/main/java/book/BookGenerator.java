package book;

import book.model.Book;
import com.github.javafaker.Faker;

import java.time.ZoneId;
import java.util.Locale;

public class BookGenerator {

    private Faker faker;
    //private static Faker faker = new Faker(new Locale("en"));

    public BookGenerator(){
        faker = new Faker();
    }
    public BookGenerator(Locale locale) {faker = new Faker(locale);}

    public Book randomBook() {
        Book book = Book.builder()
                .isbn13(faker.code().isbn13())
                .author(faker.book().author())
                .title(faker.book().title())
                .format(faker.options().option(Book.Format.class))
                .publisher(faker.book().publisher())
                .publicationDate(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .pages(faker.number().numberBetween(20,1000))
                .available(faker.bool().bool())
                .build();
        return book;
    }

}
