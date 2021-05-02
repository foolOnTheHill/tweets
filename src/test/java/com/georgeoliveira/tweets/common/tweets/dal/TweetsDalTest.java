package com.georgeoliveira.tweets.common.tweets.dal;

import com.georgeoliveira.tweets.common.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.tweets.dal.dao.TweetsDao;
import com.georgeoliveira.tweets.common.tweets.dal.dao.UsersDao;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsDalTest {
  @Inject TweetsDao tweetsDao;

  @Inject TweetsDal tweetsDal;

  @Inject UsersDao usersDao;

  @BeforeEach
  void setUpData() {
    usersDao.save(UserFixture.getDefaultUserInstance());
  }

  @AfterEach
  void cleanData() {
    tweetsDao.deleteAll();
    usersDao.deleteAll();
  }

  @Test
  void shouldPersistTweet() {
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance().toBuilder().id(null).build();

    TweetDto persistedTweet = tweetsDal.persistTweet(tweetDto);

    Assertions.assertNotNull(persistedTweet);
    Assertions.assertNotNull(persistedTweet.getId());
    Assertions.assertEquals(tweetDto.getSenderId(), persistedTweet.getSenderId());
    Assertions.assertEquals(tweetDto.getText(), persistedTweet.getText());
    Assertions.assertEquals(tweetDto.getTimestamp(), persistedTweet.getTimestamp());
  }
}
