package wishow.rihab.tweeter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import wishow.rihab.tweeter.model.entities.TweetEntity;

import java.util.List;

@Repository
public interface TweetRepository extends CrudRepository<TweetEntity, Long> {

    TweetEntity findTweetById(Long id);

    List<TweetEntity> findByUserId(Long userId);

    void deleteTweetById(Long id);

}
