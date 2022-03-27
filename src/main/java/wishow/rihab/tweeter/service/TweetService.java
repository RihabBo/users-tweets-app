package wishow.rihab.tweeter.service;


import wishow.rihab.tweeter.controller.request.NewTweetRequest;
import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.model.entities.TweetEntity;

import java.util.List;
import java.util.Optional;

public interface TweetService {

    List<Tweet> getAllTweets();

    Tweet getTweetById(Long id);

    List<Tweet> getByUserId(Long userId);

    Optional<Tweet> addTweet(NewTweetRequest newTweetRequest);

    void deleteTweet(Long id);
}
