package com.georgeoliveira.tweets.common.timelines.dal.dao;

import io.lettuce.core.dynamic.Commands;
import io.lettuce.core.dynamic.annotation.Command;

public interface TimelineCommands extends Commands {
  @Command("SET")
  void set(String userId, byte[] timelineByteArray);

  @Command("GET")
  byte[] get(String userId);
}
