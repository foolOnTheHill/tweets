package com.georgeoliveira.tweets.common.tweets.mappers;

import com.georgeoliveira.events.Key;
import com.georgeoliveira.events.Value;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import com.georgeoliveira.tweets.proto.TweetProtobuf;
import com.googlecode.protobuf.format.JsonFormat;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneOffset;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;

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

  public static TweetProtobuf.Tweet toProto(TweetDto tweetDto) {
    return TweetProtobuf.Tweet.newBuilder()
        .setId(tweetDto.getId())
        .setSenderId(tweetDto.getSenderId())
        .setText(tweetDto.getText())
        .setTimestamp(tweetDto.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli())
        .build();
  }

  @SneakyThrows
  public static TweetDto fromRecord(ConsumerRecord<Key, Value> record) throws IOException {
    String aggregate = record.value().getAggregate();
    TweetProtobuf.Tweet tweetProto = fromAggregateToProto(aggregate);
    return fromProto(tweetProto);
  }

  public static TweetDto fromProto(TweetProtobuf.Tweet tweetProto) {
    return TweetDto.builder()
        .id(tweetProto.getId())
        .senderId(tweetProto.getSenderId())
        .text(tweetProto.getText())
        .timestamp(
            Instant.ofEpochMilli(tweetProto.getTimestamp())
                .atOffset(ZoneOffset.UTC)
                .toLocalDateTime())
        .build();
  }

  @SneakyThrows
  private static TweetProtobuf.Tweet fromAggregateToProto(String aggregate) throws IOException {
    JsonFormat jsonFormat = new JsonFormat();
    TweetProtobuf.Tweet.Builder builder = TweetProtobuf.Tweet.newBuilder();
    jsonFormat.merge(IOUtils.toInputStream(aggregate, Charset.defaultCharset()), builder);
    TweetProtobuf.Tweet tweetProto = builder.build();
    return tweetProto;
  }
}
