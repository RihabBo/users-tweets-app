package wishow.rihab.tweeter.service;

import wishow.rihab.tweeter.controller.request.NewUserRequest;
import wishow.rihab.tweeter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    Optional<User> addUser(NewUserRequest newUserRequest);

    boolean updateUserName(User user, String name);

    void updateUserNameAndAge(User user, String name, int age);

    boolean deleteUser(Long id);
}
