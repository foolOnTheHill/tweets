package com.georgeoliveira.tweets.common.timelines.fixtures;

import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.proto.TimelineProtobuf;
import java.util.Collections;
import org.apache.commons.lang3.tuple.Pair;

public class TimelineFixture {
  public static TimelineDto getDefaultDtoInstance() {
    return TimelineDto.builder()
        .userId(UserFixture.DEFAULT_USER_ID)
        .tweetsList(Collections.singletonList(TweetFixture.getDefaultDtoInstance()))
        .build();
  }

  public static TimelineProtobuf.Timeline getDefaultProtoInstance() {
    return TimelineProtobuf.Timeline.newBuilder()
        .setUserId(UserFixture.DEFAULT_USER_ID)
        .addAllTweets(Collections.singletonList(TweetFixture.getDefaultProtoInstance()))
        .build();
  }

  public static Pair<Long, byte[]> getDefaultUserIdTimelineByteArrayPair() {
    return Pair.of(UserFixture.DEFAULT_USER_ID, getDefaultProtoInstance().toByteArray());
  }
}
