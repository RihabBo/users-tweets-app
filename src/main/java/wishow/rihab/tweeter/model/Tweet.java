package wishow.rihab.tweeter.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Tweet {

    private Long id;

    private Long userId;

    private String tweetContent;

    private Date createdAt;

}
