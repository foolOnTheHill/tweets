package com.georgeoliveira.tweets.api.services;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.georgeoliveira.tweets.common.timelines.dal.TimelinesDal;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.fixtures.TimelineFixture;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TimelinesServiceTest {
  @Inject TimelinesDal timelinesDal;

  @Inject TimelinesService timelinesService;

  @Test
  void shouldCallDalToRetrieveTimeline() {
    TimelineDto timelineDto = TimelineFixture.getDefaultDtoInstance();

    when(timelinesDal.getUserTimeline(anyLong())).thenReturn(Optional.of(timelineDto));

    List<TweetDto> tweetDtoList =
        timelinesService.getUserTimelineTweets(UserFixture.DEFAULT_USER_ID);

    Assertions.assertEquals(timelineDto.getTweetsList(), tweetDtoList);
  }

  @Test
  void shouldReturnEmptyListWhenNotPresent() {
    when(timelinesDal.getUserTimeline(anyLong())).thenReturn(Optional.empty());

    Assertions.assertTrue(
        timelinesService.getUserTimelineTweets(UserFixture.DEFAULT_USER_ID).isEmpty());
  }

  @MockBean(TimelinesDal.class)
  TimelinesDal timelinesDal() {
    return mock(TimelinesDal.class);
  }
}
