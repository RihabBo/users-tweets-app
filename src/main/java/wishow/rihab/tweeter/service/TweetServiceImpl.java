package wishow.rihab.tweeter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import wishow.rihab.tweeter.controller.request.NewTweetRequest;
import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.model.entities.TweetEntity;
import wishow.rihab.tweeter.model.mapper.TweetEntityToTweetMapper;
import wishow.rihab.tweeter.repository.TweetRepository;
import wishow.rihab.tweeter.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @GetMapping
    public List<Tweet> getAllTweets() {
        List<Tweet> list = new ArrayList<>();
        tweetRepository.findAll().forEach(e -> list.add(TweetEntityToTweetMapper.INSTANCE.apply(e)));
        return list;
    }

    @Override
    public Tweet getTweetById(Long id) {

        return TweetEntityToTweetMapper.INSTANCE.apply(tweetRepository.findTweetById(id));
    }

    @Override
    public List<Tweet> getByUserId(Long userId) {
        List<Tweet> tweetList = new ArrayList<>();
        tweetRepository.findByUserId(userId).forEach(e -> tweetList.add(TweetEntityToTweetMapper.INSTANCE.apply(e)));
        return tweetList;
    }

    @Override
    @Transactional
    public Optional<Tweet> addTweet(NewTweetRequest newTweetRequest) {
        return userRepository
                .findById(newTweetRequest.getUserId())
                .map(userEntity -> {
                    TweetEntity tweetEntity = new TweetEntity();
                    tweetEntity.setUser(userEntity);
                    tweetEntity.setCreatedAt(new Date());
                    tweetEntity.setTweetContent(newTweetRequest.getTweetContent());
                    return tweetRepository.save(tweetEntity);
                })
                .map(TweetEntityToTweetMapper.INSTANCE);

    }

    @Override
    @Transactional
    public void deleteTweet(Long id) {
        tweetRepository.deleteTweetById(id);
    }
}
