package wishow.rihab.tweeter.model.mapper;

import wishow.rihab.tweeter.model.Tweet;
import wishow.rihab.tweeter.model.entities.TweetEntity;

import java.util.function.Function;

public enum TweetEntityToTweetMapper implements Function<TweetEntity, Tweet> {
    INSTANCE;

    @Override
    public Tweet apply(TweetEntity tweetEntity) {
        if (tweetEntity != null) {
            return new Tweet(tweetEntity.getId(), tweetEntity.getUser().getId(), tweetEntity.getTweetContent(), tweetEntity.getCreatedAt());
        } else {
            return null;
        }
    }
}