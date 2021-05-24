package com.georgeoliveira.tweets.common.tweets.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@EqualsAndHashCode
@Jacksonized
public class TweetDto {
  @EqualsAndHashCode.Exclude Long id;

  @JsonProperty("senderId")
  Long senderId;

  String text;

  LocalDateTime timestamp;
}
