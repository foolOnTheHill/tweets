package com.georgeoliveira.tweets.common.tweets.outbox.mappers;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.fixtures.TweetOutboxFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetOutboxMapperTest {
  @Test
  void shouldMapCorrectlyToOutbox() {
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance();
    TweetOutbox expectedOutbox = TweetOutboxFixture.getDefaultOutboxInstance();

    TweetOutbox outbox =
        TweetOutboxMapper.toOutboxModel(tweetDto, TweetOutboxFixture.DEFAULT_EVENT_TYPE);

    Assertions.assertEquals(expectedOutbox, outbox);
  }
}
