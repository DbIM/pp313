package kata.pp311.controller;

import kata.pp311.model.User;
import kata.pp311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class RestCotroller {
    private final UserService userService;

    @Autowired
    public RestCotroller(UserService userService) {
        this.userService = userService;
    }

    //read all users
    @GetMapping("admin/restusers")
    public List<User> list() {
        return userService.getAllUsers();
    }

    //read exact user
    @GetMapping("restusers/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //userPage
    @GetMapping("/userGet")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    //create user
    @PostMapping("admin/restusers/saveUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    //update user
    @PutMapping("admin/restusers/updateUser")
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    //delete user
    @DeleteMapping("admin/restusers/removeUser/{id}")
    public void delete(@PathVariable Long id) {
        userService.removeUser(id);
    }
}
