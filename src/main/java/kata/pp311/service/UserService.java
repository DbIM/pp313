package kata.pp311.service;

import kata.pp311.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public void saveUser(User user);
    public User updateUser(User user);
    public User getUserById(Long id);
    public User getUserByName(String name);
    void removeUser(Long id);
}
