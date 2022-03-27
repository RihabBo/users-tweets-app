package wishow.rihab.tweeter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishow.rihab.tweeter.model.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserById(Long userId);

    void deleteUserById(Long id);

}
