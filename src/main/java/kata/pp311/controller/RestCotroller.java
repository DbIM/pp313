package kata.pp311.controller;

import kata.pp311.model.User;
import kata.pp311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestCotroller {
    private final UserService userService;

    @Autowired
    public RestCotroller(UserService userService) {
        this.userService = userService;
    }

    //view all users
    @GetMapping("admin/restusers")
    public List<User> list() {
        return userService.getAllUsers();
    }

    //view exact user
    @GetMapping("admin/restusers/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //create user
    @PostMapping("admin/restusers/saveUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }
/*    fetch(
  '/admin/restusers/saveUser',
    {
        method: 'POST',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: 'test', lastname: 'test', password: 'test' })
    }
).then(result => result.json().then(console.log))*/

    //edit user
    @PutMapping("admin/restusers/updateUser")
    public ResponseEntity<User> update(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

/*    fetch(
  '/admin/restusers/updateUser',
    {
        method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: '1', name: 'Pro', password: 'admin' })
    }
).then(result => result.json().then(console.log));*/


    @DeleteMapping("admin/restusers/removeUser/{id}")
    public void delete(@PathVariable Long id) {
        userService.removeUser(id);
    }

/*    fetch("/admin/restusers/removeUser/" + 3, {
        method: "DELETE"
    });*/


}
