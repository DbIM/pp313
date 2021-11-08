package kata.pp311.dao;

import org.springframework.stereotype.Component;
import kata.pp311.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public User getUserByName(String name) {
        Query query = entityManager.createQuery("FROM User WHERE username = :name");
        query.setParameter("name", name);
        return (User) query.getSingleResult();
    }
}