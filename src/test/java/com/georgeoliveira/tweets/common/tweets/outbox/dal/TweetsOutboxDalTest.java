package com.georgeoliveira.tweets.common.tweets.outbox.dal;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.dal.dao.TweetsOutboxDao;
import com.georgeoliveira.tweets.common.tweets.outbox.fixtures.TweetOutboxFixture;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsOutboxDalTest {
  @Inject TweetsOutboxDao tweetsOutboxDao;

  @Inject TweetsOutboxDal tweetsOutboxDal;

  @AfterEach
  void cleanData() {
    tweetsOutboxDao.deleteAll();
  }

  @Test
  void shouldPersistTweetToOutbox() {
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance();
    TweetOutbox tweetOutbox = TweetOutboxFixture.getDefaultOutboxInstance();

    tweetsOutboxDal.sendToOutbox(tweetDto, TweetOutboxFixture.DEFAULT_EVENT_TYPE);

    List<TweetOutbox> persistedOutboxes = tweetsOutboxDao.findAll();

    Assertions.assertEquals(Collections.singletonList(tweetOutbox), persistedOutboxes);
  }
}
