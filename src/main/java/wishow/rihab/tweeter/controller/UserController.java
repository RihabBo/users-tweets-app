package wishow.rihab.tweeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishow.rihab.tweeter.controller.request.NewUserRequest;
import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.model.User;
import wishow.rihab.tweeter.service.TweetService;
import wishow.rihab.tweeter.service.UserService;

import java.util.List;

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
    public User getUserById(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
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

    @PatchMapping(value = "/{userName}")
    public ResponseEntity<Void> updateUserByName(@RequestBody User user, @PathVariable(value = "userName") String userName) {
        if (userService.updateUserName(user, userName)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
