package com.georgeoliveira.tweets.common.timelines.dal;

import com.georgeoliveira.tweets.common.timelines.dal.dao.TimelineCommands;
import com.georgeoliveira.tweets.common.timelines.dtos.TimelineDto;
import com.georgeoliveira.tweets.common.timelines.mappers.TimelineMapper;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.apache.commons.lang3.tuple.Pair;

@Singleton
public class TimelinesDal {
  @Inject TimelineCommands timelineCommandsDao;

  public void persistTimeline(TimelineDto timelineDto) {
    Pair<Long, byte[]> userIdTimelinePair =
        TimelineMapper.toUserIdTimelineByteArrayPair(timelineDto);
    timelineCommandsDao.set(
        String.valueOf(userIdTimelinePair.getLeft()), userIdTimelinePair.getRight());
  }

  public Optional<TimelineDto> getUserTimeline(Long userId) {
    byte[] timelineByteArray = timelineCommandsDao.get(String.valueOf(userId));
    return TimelineMapper.fromByteArray(timelineByteArray);
  }
}
