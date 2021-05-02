package com.georgeoliveira.tweets.common.tweets.outbox.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public enum EventType {
  PUBLISH_TWEET("publish_tweet");

  String eventType;
}
