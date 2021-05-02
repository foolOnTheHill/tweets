package com.georgeoliveira.tweets.api.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsServiceTest {
  @Inject TweetsDal tweetsDal;

  @Inject TweetsService tweetsService;

  @Test
  void shouldCallDalToPersistTweet() {
    PostTweetRequestDto postTweetRequestDto = TweetFixture.getDefaultPostTweetRequestDtoInstance();
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance().toBuilder().id(null).build();

    tweetsService.publishTweet(postTweetRequestDto);

    verify(tweetsDal).persistTweet(tweetDto);
  }

  @MockBean(TweetsDal.class)
  TweetsDal tweetsDal() {
    return mock(TweetsDal.class);
  }
}
