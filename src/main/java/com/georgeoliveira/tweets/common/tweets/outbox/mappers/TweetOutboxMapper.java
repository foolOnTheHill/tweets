package com.georgeoliveira.tweets.common.tweets.outbox.mappers;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.mappers.TweetMapper;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import com.georgeoliveira.tweets.proto.TweetProtobuf;
import com.googlecode.protobuf.format.JsonFormat;

public class TweetOutboxMapper {
  public static TweetOutbox toOutboxModel(TweetDto tweetDto, EventType eventType) {
    TweetProtobuf.Tweet tweetProto = TweetMapper.toProto(tweetDto);
    String aggregate = toAggregate(tweetProto);

    TweetOutbox tweetOutbox = new TweetOutbox();
    tweetOutbox.setType(eventType.toString());
    tweetOutbox.setAggregate(aggregate);
    tweetOutbox.setAggregateId(String.valueOf(tweetDto.getId()));

    return tweetOutbox;
  }

  private static String toAggregate(TweetProtobuf.Tweet tweetProto) {
    JsonFormat jsonFormat = new JsonFormat();
    return jsonFormat.printToString(tweetProto);
  }
}
