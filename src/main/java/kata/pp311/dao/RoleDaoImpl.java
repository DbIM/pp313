package kata.pp311.dao;

import kata.pp311.model.User;
import org.springframework.stereotype.Component;
import kata.pp311.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }
}
