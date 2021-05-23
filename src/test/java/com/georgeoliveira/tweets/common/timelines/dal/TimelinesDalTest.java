package com.georgeoliveira.tweets.common.timelines.dal;

import com.georgeoliveira.tweets.common.timelines.dal.dao.TimelineCommands;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.fixtures.TimelineFixture;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TimelinesDalTest {
  @Inject TimelinesDal timelinesDal;

  @Inject TimelineCommands timelineCommands;

  @Test
  void shouldPersistTimeline() {
    TimelineDto dto = TimelineFixture.getDefaultDtoInstance();
    Pair<Long, byte[]> pair = TimelineFixture.getDefaultUserIdTimelineByteArrayPair();

    timelinesDal.persistTimeline(dto);

    byte[] persistedDtoByteArray = timelineCommands.get(String.valueOf(dto.getUserId()));

    Assertions.assertArrayEquals(pair.getRight(), persistedDtoByteArray);
  }
}
