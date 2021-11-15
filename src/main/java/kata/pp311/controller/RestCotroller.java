package kata.pp311.controller;

import kata.pp311.model.User;
import kata.pp311.service.RoleService;
import kata.pp311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class RestCotroller {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RestCotroller(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //Rest block
    @GetMapping
    public List<User> list() {
        return userService.getAllUsers();
    }
}
