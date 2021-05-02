package com.georgeoliveira.tweets.api.dtos;

import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@Introspected
public class PostTweetRequestDto {
  @NotNull @NotBlank Long senderId;
  @NotNull @NotBlank String text;
  @NotNull @Positive Long timestamp;
}
