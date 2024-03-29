package com.georgeoliveira.tweets.common.timelines.mappers;

import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.mappers.TweetMapper;
import com.georgeoliveira.tweets.proto.TimelineProtobuf;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;

public class TimelineMapper {
  public static TimelineDto fromList(Long userId, List<TweetDto> tweetsList) {
    return TimelineDto.builder().userId(userId).tweetsList(tweetsList).build();
  }

  public static Optional<TimelineDto> fromByteArray(byte[] timelineByteArray) {
    try {
      TimelineProtobuf.Timeline timelineProto =
          TimelineProtobuf.Timeline.parseFrom(timelineByteArray);
      TimelineDto timelineDto = fromProto(timelineProto);
      return Optional.of(timelineDto);
    } catch (InvalidProtocolBufferException | NullPointerException e) {
      return Optional.empty();
    }
  }

  public static Pair<Long, byte[]> toUserIdTimelineByteArrayPair(TimelineDto timelineDto) {
    return Pair.of(timelineDto.getUserId(), toTimelineByteArray(timelineDto));
  }

  private static byte[] toTimelineByteArray(TimelineDto timelineDto) {
    return toProto(timelineDto).toByteArray();
  }

  private static TimelineProtobuf.Timeline toProto(TimelineDto timelineDto) {
    return TimelineProtobuf.Timeline.newBuilder()
        .setUserId(timelineDto.getUserId())
        .addAllTweets(
            timelineDto
                .getTweetsList()
                .stream()
                .map(tweetDto -> TweetMapper.toProto(tweetDto))
                .collect(Collectors.toList()))
        .build();
  }

  private static TimelineDto fromProto(TimelineProtobuf.Timeline timelineProto) {
    return TimelineDto.builder()
        .userId(timelineProto.getUserId())
        .tweetsList(
            timelineProto
                .getTweetsList()
                .stream()
                .map(TweetMapper::fromProto)
                .collect(Collectors.toList()))
        .build();
  }
}
