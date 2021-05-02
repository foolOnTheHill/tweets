package com.georgeoliveira.tweets.api.mappers;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TweetRequestMapper {
  public static TweetDto fromPostRequest(PostTweetRequestDto postTweetRequestDto) {
    return TweetDto.builder()
        .id(null)
        .senderId(postTweetRequestDto.getSenderId())
        .text(postTweetRequestDto.getText())
        .timestamp(
            LocalDateTime.from(
                Instant.ofEpochMilli(postTweetRequestDto.getTimestamp()).atZone(ZoneOffset.UTC)))
        .build();
  }
}
