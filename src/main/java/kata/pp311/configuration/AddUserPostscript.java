package kata.pp311.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import kata.pp311.model.Role;
import kata.pp311.model.User;
import kata.pp311.service.RoleService;
import kata.pp311.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class AddUserPostscript {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AddUserPostscript(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Init() {
        Role adminRole = new Role("ADMIN");
        roleService.saveRole(adminRole);

        Role userRole = new Role("USER");
        roleService.saveRole(userRole);

        Set<Role> adminRoles = new HashSet<Role>(Collections.singleton(adminRole));
        User adminUser = new User("admin", "admin", "1", adminRoles);
        userService.saveUser(adminUser);

        Set<Role> userRoles = new HashSet<Role>(Collections.singleton(userRole));
        User userUser = new User("user", "user", "1", userRoles);
        userService.saveUser(userUser);
    }
}