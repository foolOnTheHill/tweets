package com.georgeoliveira.tweets.worker.processors;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgeoliveira.tweets.common.timelines.dal.TimelinesDal;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.fixtures.TimelineFixture;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.users.dal.UsersDal;
import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.users.mappers.UserMapper;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsProcessorTest {
  @Inject TweetsProcessor tweetsProcessor;

  @Inject TimelinesDal timelinesDal;

  @Inject TweetsDal tweetsDal;

  @Inject UsersDal usersDal;

  @Test
  void shouldCallDalToPersistTweetAuthorFollowersTimelines() {
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance();

    List<UserDto> userFollowers =
        UserFixture.getUsersSequence(1)
            .stream()
            .map(UserMapper::fromModel)
            .collect(Collectors.toList());

    when(usersDal.getUserFollowers(tweetDto.getSenderId())).thenReturn(userFollowers);
    when(tweetsDal.getTimelineForUser(anyLong(), anyLong()))
        .thenReturn(Collections.singletonList(TweetFixture.getDefaultDtoInstance()));

    tweetsProcessor.accept(tweetDto);

    TimelineDto expectedTimelineDto =
        TimelineFixture.getDefaultDtoInstance()
            .toBuilder()
            .userId(userFollowers.get(0).getId())
            .build();

    verify(timelinesDal, times(1)).persistTimeline(expectedTimelineDto);
  }

  @MockBean(TimelinesDal.class)
  TimelinesDal timelinesDal() {
    return mock(TimelinesDal.class);
  }

  @MockBean(TweetsDal.class)
  TweetsDal tweetsDal() {
    return mock(TweetsDal.class);
  }

  @MockBean(UsersDal.class)
  UsersDal usersDal() {
    return mock(UsersDal.class);
  }
}
