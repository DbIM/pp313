package kata.pp311.service;

import kata.pp311.model.Role;

import java.util.List;

public interface RoleService {
    public void saveRole(Role role);
    public List<Role> getAllRoles();
    public Role getRoleById(Long id);
    public Role getRoleByName(String name);
}
