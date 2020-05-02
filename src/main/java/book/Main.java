package book;

import book.model.Book;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;

@Log4j2
public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");

    private static void generateBook(int n) {
        BookGenerator bookGenerator = new BookGenerator(new Locale("hu"));
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 0; i < n; i++) {
                em.persist(bookGenerator.randomBook());
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    private static void listBooks(){
        EntityManager em = emf.createEntityManager();
        try {
            em.createQuery("SELECT b FROM Book b", Book.class).getResultStream().forEach(log::info);
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        try{
            int n = args.length > 0 ? Integer.parseInt(args[0]) : 1000;
            generateBook(n);
            listBooks();
        } finally {
            emf.close();
        }
    }
}
