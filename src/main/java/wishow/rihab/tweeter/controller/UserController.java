package wishow.rihab.tweeter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wishow.rihab.tweeter.controller.error.ApiError;
import wishow.rihab.tweeter.controller.input.PatchUserInput;
import wishow.rihab.tweeter.controller.request.NewUserRequest;
import wishow.rihab.tweeter.controller.request.PatchUserRequest;
import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.model.User;
import wishow.rihab.tweeter.service.TweetService;
import wishow.rihab.tweeter.service.UserService;
import wishow.rihab.tweeter.service.UserUpdateResponse;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;


    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {
        if (userService.getUserById(userId) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody NewUserRequest newUserRequest) {
        return userService.addUser(newUserRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") Long userId) {

        if (userService.deleteUser(userId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{userId}/tweets")
    public List<Tweet> getTweetByUserId(@PathVariable(value = "userId") Long userId) {
        return tweetService.getByUserId(userId);

    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<ApiError> updateUserNameAndAge(@RequestBody PatchUserInput userInput, @PathVariable(value = "userId") Long userId) {
        try {
            PatchUserRequest patchUserRequest = new PatchUserRequest(userInput);
            User userToUpdate =
                    userService.getUserById(userId);
            if (userToUpdate != null) {
                UserUpdateResponse userUpdateResponse = userService.updateUserNameAndAge(patchUserRequest, userToUpdate);
                if (UserUpdateResponse.USER_UPDATED == userUpdateResponse || UserUpdateResponse.NOTHING_TO_UPDATE == userUpdateResponse) {
                    return ResponseEntity.noContent().build();
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(ApiError.builder().code(404).message("The user was not found").build());
        } catch (IllegalArgumentException e) {
            log.warn("Cannot update user", e);
            return ResponseEntity.badRequest().body(ApiError.builder().code(400).message("The name or the age of the user is not valid").build());
        }
    }
}