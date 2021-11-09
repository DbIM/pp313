package kata.pp311.dao;

import kata.pp311.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();

    Role getRoleById(long id);

    Role getByName(String roleName);

    Role save(Role role);
}