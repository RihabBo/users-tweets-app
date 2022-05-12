package wishow.rihab.tweeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<Tweet> getTweetById(@PathVariable(value = "tweetId") Long tweetId) {
        if (tweetService.getTweetById(tweetId) != null) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

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
