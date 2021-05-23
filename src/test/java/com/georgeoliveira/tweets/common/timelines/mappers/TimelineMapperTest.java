package com.georgeoliveira.tweets.common.timelines.mappers;

import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.fixtures.TimelineFixture;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import java.util.Collections;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TimelineMapperTest {
  @Test
  void shouldMapCorrectlyFromList() {
    TimelineDto timelineDto =
        TimelineMapper.fromList(
            UserFixture.DEFAULT_USER_ID,
            Collections.singletonList(TweetFixture.getDefaultDtoInstance()));
    TimelineDto expectedDto = TimelineFixture.getDefaultDtoInstance();

    Assertions.assertEquals(expectedDto, timelineDto);
  }

  @Test
  void shouldMapCorrectlyToUserIdTimelineByteArrayPair() {
    Pair<Long, byte[]> pair =
        TimelineMapper.toUserIdTimelineByteArrayPair(TimelineFixture.getDefaultDtoInstance());
    Pair<Long, byte[]> expectedPair = TimelineFixture.getDefaultUserIdTimelineByteArrayPair();

    Assertions.assertArrayEquals(expectedPair.getRight(), pair.getRight());
    Assertions.assertEquals(expectedPair.getLeft(), pair.getLeft());
  }
}
