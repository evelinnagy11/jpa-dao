package user;

import java.util.Optional;

import javax.persistence.NoResultException;

import com.google.inject.persist.Transactional;

import jpa.GenericJpaDao;
import user.model.User;

public class UserDao extends GenericJpaDao<User> {

    @Transactional
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.of(entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
