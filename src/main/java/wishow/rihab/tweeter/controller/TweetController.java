package wishow.rihab.tweeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wishow.rihab.tweeter.controller.request.NewTweetRequest;
import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.service.TweetService;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {


    @Autowired
    private TweetService tweetService;

    @GetMapping
    public List<Tweet> getTweets() {
        return tweetService.getAllTweets();
    }


    @GetMapping(value = "/{tweetId}")
    public Tweet getTweetById(@PathVariable(value = "tweetId") Long tweetId) {
        return tweetService.getTweetById(tweetId);

    }


    @PostMapping
    public ResponseEntity<Tweet> addTweet(@RequestBody NewTweetRequest newTweetRequest) {
        return tweetService.addTweet(newTweetRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

    @DeleteMapping(value = "/{tweetId}")
    public void deleteTweet(@PathVariable(value = "tweetId") Long tweetId) {
        tweetService.deleteTweet(tweetId);
    }

}
