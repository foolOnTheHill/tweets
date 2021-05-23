package com.georgeoliveira.tweets.common.tweets.fixtures;

import com.georgeoliveira.events.Key;
import com.georgeoliveira.events.Value;
import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.proto.TweetProtobuf;
import com.googlecode.protobuf.format.JsonFormat;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class TweetFixture {
  public static final Long DEFAULT_ID = 123L;
  public static final LocalDateTime DEFAULT_LOCAL_DATE_TIME =
      LocalDateTime.of(2021, 3, 20, 14, 30, 00);
  public static final String DEFAULT_TEXT = "This is a tweet";
  public static final UUID DEFAULT_EVENT_ID = UUID.randomUUID();

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

  public static ConsumerRecord<Key, Value> getDefaultConsumerRecordInstance() {
    JsonFormat jsonFormat = new JsonFormat();
    String aggregate = jsonFormat.printToString(getDefaultProtoInstance());

    Key key = Key.newBuilder().setAggregateId(String.valueOf(DEFAULT_ID)).build();
    Value value =
        Value.newBuilder()
            .setAggregateId(String.valueOf(DEFAULT_ID))
            .setEventId(DEFAULT_EVENT_ID.toString())
            .setType(EventType.PUBLISH_TWEET.toString())
            .setAggregate(aggregate)
            .build();

    ConsumerRecord<Key, Value> record = new ConsumerRecord<>("", 0, 0L, key, value);

    return record;
  }
}
