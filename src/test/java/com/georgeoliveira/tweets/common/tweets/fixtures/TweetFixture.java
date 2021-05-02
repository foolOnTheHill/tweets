package com.georgeoliveira.tweets.common.tweets.fixtures;

import com.georgeoliveira.campaigns.proto.TweetProtobuf;
import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TweetFixture {
  public static final Long DEFAULT_ID = 123L;
  public static final LocalDateTime DEFAULT_LOCAL_DATE_TIME =
      LocalDateTime.of(2021, 3, 20, 14, 30, 00);
  public static final String DEFAULT_TEXT = "This is a tweet";

  public static TweetDto getDefaultDtoInstance() {
    return TweetDto.builder()
        .id(DEFAULT_ID)
        .senderId(UserFixture.DEFAULT_USER_ID)
        .text(DEFAULT_TEXT)
        .timestamp(DEFAULT_LOCAL_DATE_TIME)
        .build();
  }

  public static Tweet getDefaultModelInstance() {
    Tweet tweet = new Tweet();
    tweet.setId(DEFAULT_ID);
    tweet.setSenderId(UserFixture.DEFAULT_USER_ID);
    tweet.setText(DEFAULT_TEXT);
    tweet.setTimestamp(Timestamp.from(DEFAULT_LOCAL_DATE_TIME.toInstant(ZoneOffset.UTC)));
    return tweet;
  }

  public static PostTweetRequestDto getDefaultPostTweetRequestDtoInstance() {
    return PostTweetRequestDto.builder()
        .senderId(UserFixture.DEFAULT_USER_ID)
        .text(DEFAULT_TEXT)
        .timestamp(DEFAULT_LOCAL_DATE_TIME.toInstant(ZoneOffset.UTC).toEpochMilli())
        .build();
  }

  public static TweetProtobuf.Tweet getDefaultProtoInstance() {
    return TweetProtobuf.Tweet.newBuilder()
        .setId(DEFAULT_ID)
        .setSenderId(UserFixture.DEFAULT_USER_ID)
        .setText(DEFAULT_TEXT)
        .setTimestamp(DEFAULT_LOCAL_DATE_TIME.toInstant(ZoneOffset.UTC).toEpochMilli())
        .build();
  }
}
