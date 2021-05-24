package com.georgeoliveira.tweets.api.services;

import com.georgeoliveira.tweets.common.timelines.dal.TimelinesDal;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TimelinesService {
  @Inject TimelinesDal timelinesDal;

  public List<TweetDto> getUserTimelineTweets(Long userId) {
    return timelinesDal
        .getUserTimeline(userId)
        .map(TimelineDto::getTweetsList)
        .orElse(Collections.emptyList());
  }
}
