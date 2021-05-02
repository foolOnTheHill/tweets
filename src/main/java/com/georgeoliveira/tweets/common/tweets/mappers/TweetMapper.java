package com.georgeoliveira.tweets.common.tweets.mappers;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import java.sql.Timestamp;
import java.time.ZoneOffset;

public class TweetMapper {
  public static TweetDto fromModel(Tweet tweet) {
    return TweetDto.builder()
        .id(tweet.getId())
        .senderId(tweet.getSenderId())
        .text(tweet.getText())
        .timestamp(tweet.getTimestamp().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime())
        .build();
  }

  public static Tweet fromDto(TweetDto tweetDto) {
    Tweet tweet = new Tweet();
    tweet.setId(tweetDto.getId());
    tweet.setSenderId(tweetDto.getSenderId());
    tweet.setText(tweetDto.getText());
    tweet.setTimestamp(Timestamp.from(tweetDto.getTimestamp().toInstant(ZoneOffset.UTC)));
    return tweet;
  }
}
