package com.georgeoliveira.tweets.common.tweets.dtos;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode
public class TweetDto {
  @EqualsAndHashCode.Exclude Long id;
  Long senderId;
  String text;
  LocalDateTime timestamp;
}
