package wishow.rihab.tweeter.model.mapper;

import wishow.rihab.tweeter.model.User;
import wishow.rihab.tweeter.model.entities.UserEntity;

import java.util.function.Function;

public enum UserEntityToUserMapper implements Function<UserEntity, User> {
    INSTANCE;

    @Override
    public User apply(UserEntity userEntity) {

        return new User(userEntity.getId(), userEntity.getName(), userEntity.getAge());
    }
}
