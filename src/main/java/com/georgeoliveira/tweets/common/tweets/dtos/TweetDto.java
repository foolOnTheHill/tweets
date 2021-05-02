package com.georgeoliveira.tweets.common.tweets.dtos;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class TweetDto {
  Long id;
  Long senderId;
  String text;
  LocalDateTime timestamp;
}
