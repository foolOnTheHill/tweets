package com.georgeoliveira.tweets.common.timelines.dal.dao.factories;

import com.georgeoliveira.tweets.common.timelines.dal.dao.TimelineCommands;
import io.lettuce.core.RedisClient;
import io.lettuce.core.dynamic.RedisCommandFactory;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import javax.inject.Singleton;

@Factory
public class TimelineCommandsFactory {
  @Value("${redis.host}")
  private String REDIS_HOST;

  @Singleton
  TimelineCommands timelineCommands() {
    RedisClient redisClient = RedisClient.create(REDIS_HOST);
    RedisCommandFactory commandFactory = new RedisCommandFactory(redisClient.connect());
    return commandFactory.getCommands(TimelineCommands.class);
  }
}
