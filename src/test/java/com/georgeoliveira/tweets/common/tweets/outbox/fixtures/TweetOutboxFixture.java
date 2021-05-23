package com.georgeoliveira.tweets.common.tweets.outbox.fixtures;

import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import com.georgeoliveira.tweets.proto.TweetProtobuf;
import com.googlecode.protobuf.format.JsonFormat;

public class TweetOutboxFixture {
  public static final EventType DEFAULT_EVENT_TYPE = EventType.PUBLISH_TWEET;

  public static TweetOutbox getDefaultOutboxInstance() {
    TweetProtobuf.Tweet tweetProto = TweetFixture.getDefaultProtoInstance();

    JsonFormat jsonFormat = new JsonFormat();
    String aggregate = jsonFormat.printToString(tweetProto);

    TweetOutbox tweetOutbox = new TweetOutbox();
    tweetOutbox.setAggregate(aggregate);
    tweetOutbox.setAggregateId(String.valueOf(tweetProto.getId()));
    tweetOutbox.setType(DEFAULT_EVENT_TYPE.toString());

    return tweetOutbox;
  }
}
