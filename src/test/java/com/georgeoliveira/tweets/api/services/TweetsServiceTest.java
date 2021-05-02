package com.georgeoliveira.tweets.api.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.dal.TweetsOutboxDal;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsServiceTest {
  @Inject TweetsDal tweetsDal;

  @Inject TweetsOutboxDal tweetsOutboxDal;

  @Inject TweetsService tweetsService;

  @Test
  void shouldCallDalsToPersistTweetAndSendToOutbox() {
    PostTweetRequestDto postTweetRequestDto = TweetFixture.getDefaultPostTweetRequestDtoInstance();
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance().toBuilder().id(null).build();

    when(tweetsDal.persistTweet(any())).thenReturn(tweetDto);

    tweetsService.publishTweet(postTweetRequestDto);

    verify(tweetsDal).persistTweet(tweetDto);
    verify(tweetsOutboxDal).sendToOutbox(tweetDto, EventType.PUBLISH_TWEET);
  }

  @MockBean(TweetsDal.class)
  TweetsDal tweetsDal() {
    return mock(TweetsDal.class);
  }

  @MockBean(TweetsOutboxDal.class)
  TweetsOutboxDal tweetsOutboxDal() {
    return mock(TweetsOutboxDal.class);
  }
}
