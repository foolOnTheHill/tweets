package com.georgeoliveira.tweets.api.mappers;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetRequestMapperTest {
  @Test
  void shouldMapCorrectlyFromPostTweetRequest() {
    PostTweetRequestDto postTweetRequestDto = TweetFixture.getDefaultPostTweetRequestDtoInstance();
    TweetDto dto = TweetRequestMapper.fromPostRequest(postTweetRequestDto);
    TweetDto expectedDto = TweetFixture.getDefaultDtoInstance().toBuilder().id(null).build();

    Assertions.assertEquals(expectedDto, dto);
  }
}
