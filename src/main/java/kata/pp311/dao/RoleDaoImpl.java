package kata.pp311.dao;

import org.springframework.stereotype.Component;
import kata.pp311.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getByName(String role) {
        Query query = entityManager.createQuery("FROM Role WHERE role = :role");
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }

    @Override
    public Role save(Role role) {
        entityManager.persist(role);
        return role;
    }
}