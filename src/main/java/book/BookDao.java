package book;

import book.model.Book;

import javax.persistence.NoResultException;
import java.util.Optional;

import com.google.inject.persist.Transactional;
import jpa.GenericJpaDao;

public class BookDao extends GenericJpaDao<Book> {

    @Transactional
    public Optional<Book> findByIsbn13(String isbn13){
        try {
            return Optional.of(entityManager.createQuery("SELECT b FROM Book b WHERE b.isbn13 = :isbn13", Book.class)
                    .setParameter("ISBN", isbn13)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
