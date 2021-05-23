package com.georgeoliveira.tweets.worker.processors;

import com.georgeoliveira.tweets.common.timelines.dal.TimelinesDal;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.mappers.TimelineMapper;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.users.dal.UsersDal;
import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetsProcessor implements Consumer<TweetDto> {
  @Inject TimelinesDal timelinesDal;

  @Inject TweetsDal tweetsDal;

  @Inject UsersDal usersDal;

  @Override
  public void accept(TweetDto tweetDto) {
    List<UserDto> authorFollowers = usersDal.getUserFollowers(tweetDto.getSenderId());
    List<TimelineDto> timelineDtos =
        authorFollowers
            .stream()
            .map(UserDto::getId)
            .map(
                userId ->
                    TimelineMapper.fromList(userId, tweetsDal.getTimelineForUser(userId, 100L)))
            .collect(Collectors.toList());

    timelineDtos.forEach(timelineDto -> timelinesDal.persistTimeline(timelineDto));
  }
}
