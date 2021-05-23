package com.georgeoliveira.tweets.common.timelines.dtos;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Builder(toBuilder = true)
@Value
public class TimelineDto {
  Long userId;
  List<TweetDto> tweetsList;
}
