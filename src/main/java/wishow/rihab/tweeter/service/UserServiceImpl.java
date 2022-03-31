package wishow.rihab.tweeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wishow.rihab.tweeter.controller.request.NewUserRequest;
import wishow.rihab.tweeter.model.User;
import wishow.rihab.tweeter.model.entities.UserEntity;
import wishow.rihab.tweeter.model.mapper.UserEntityToUserMapper;
import wishow.rihab.tweeter.repository.TweetRepository;
import wishow.rihab.tweeter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(e -> users.add(UserEntityToUserMapper.INSTANCE.apply(e)));
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        return UserEntityToUserMapper.INSTANCE.apply(userRepository.findUserById(userId));
    }

    @Override
    @Transactional
    public Optional<User> addUser(NewUserRequest newUserRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(newUserRequest.getName());
        userEntity.setAge(newUserRequest.getAge());
        return Optional.of(UserEntityToUserMapper.INSTANCE.apply(userRepository.save(userEntity)));

    }

    @Override
    @Transactional
    public boolean updateUserName(User user, String name) {
        UserEntity userEntity = userRepository.findUserById(user.getId());
        if (userEntity != null) {
            userEntity.setName(name);
            userRepository.save(userEntity);
            return true;
        } else {
            return false;
        }

    }

    @Override
    @Transactional
    public boolean updateUserNameAndAge(User user, String name, int age) {
        UserEntity userEntity = userRepository.findUserById(user.getId());
        if (userEntity != null) {
            userEntity.setName(name);
            userEntity.setAge(age);
            userRepository.save(userEntity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            List<Long> tweetIds = tweetRepository.findByUserId(id)
                    .stream()
                    .map(t -> t.getUser().getId())
                    .collect(Collectors.toList());
            tweetRepository.deleteAllById(tweetIds);
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
