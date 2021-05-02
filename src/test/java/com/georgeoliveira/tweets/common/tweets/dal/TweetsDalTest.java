package com.georgeoliveira.tweets.common.tweets.dal;

import com.georgeoliveira.tweets.common.tweets.dal.dao.TweetsDao;
import com.georgeoliveira.tweets.common.tweets.dal.dao.UsersDao;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
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
    Tweet tweet = TweetFixture.getDefaultModelInstance();

    tweetsDal.persistTweet(tweetDto);

    List<Tweet> persistedTweets = tweetsDao.findAll();

    Assertions.assertEquals(Collections.singletonList(tweet), persistedTweets);
  }
}
