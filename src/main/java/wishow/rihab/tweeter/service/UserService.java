package wishow.rihab.tweeter.service;

import wishow.rihab.tweeter.controller.input.PatchUserInput;
import wishow.rihab.tweeter.controller.request.NewUserRequest;
import wishow.rihab.tweeter.controller.request.PatchUserRequest;
import wishow.rihab.tweeter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    Optional<User> addUser(NewUserRequest newUserRequest);

    UserUpdateResponse updateUserNameAndAge(PatchUserRequest patchUserRequest, User user);

    boolean deleteUser(Long id);
}
