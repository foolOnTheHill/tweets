package com.georgeoliveira.tweets.common.tweets.outbox.mappers;

import com.georgeoliveira.campaigns.proto.TweetProtobuf;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import com.googlecode.protobuf.format.JsonFormat;
import java.time.ZoneOffset;

public class TweetOutboxMapper {
  public static TweetOutbox toOutboxModel(TweetDto tweetDto, EventType eventType) {
    TweetProtobuf.Tweet tweetProto = toProto(tweetDto);
    String aggregate = toAggregate(tweetProto);

    TweetOutbox tweetOutbox = new TweetOutbox();
    tweetOutbox.setType(eventType.toString());
    tweetOutbox.setAggregate(aggregate);
    tweetOutbox.setAggregateId(String.valueOf(tweetDto.getId()));

    return tweetOutbox;
  }

  private static TweetProtobuf.Tweet toProto(TweetDto tweetDto) {
    return TweetProtobuf.Tweet.newBuilder()
        .setId(tweetDto.getId())
        .setSenderId(tweetDto.getSenderId())
        .setText(tweetDto.getText())
        .setTimestamp(tweetDto.getTimestamp().toInstant(ZoneOffset.UTC).toEpochMilli())
        .build();
  }

  private static String toAggregate(TweetProtobuf.Tweet tweetProto) {
    JsonFormat jsonFormat = new JsonFormat();
    return jsonFormat.printToString(tweetProto);
  }
}
