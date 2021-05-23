package com.georgeoliveira.tweets.common.tweets.dal;

import com.georgeoliveira.tweets.common.tweets.dal.dao.TweetsDao;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.mappers.TweetMapper;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import com.georgeoliveira.tweets.common.users.dal.dao.UsersDao;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.users.models.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsDalTest {
  @Inject TweetsDao tweetsDao;

  @Inject TweetsDal tweetsDal;

  @Inject UsersDao usersDao;

  @AfterEach
  void cleanData() {
    tweetsDao.deleteAll();
    usersDao.deleteAll();
  }

  @Test
  void shouldPersistTweet() {
    usersDao.save(UserFixture.getDefaultUserInstance());

    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance().toBuilder().id(null).build();
    Tweet tweet = TweetFixture.getDefaultModelInstance();

    tweetsDal.persistTweet(tweetDto);

    List<Tweet> persistedTweets = tweetsDao.findAll();

    Assertions.assertEquals(Collections.singletonList(tweet), persistedTweets);
  }

  @Test
  void shouldGetTimelineTweets() {
    List<User> users = UserFixture.getUsersSequence(2);

    User firstUser = users.get(0);
    User secondUser = users.get(1);

    firstUser.setFollowers(Collections.singletonList(secondUser));

    usersDao.saveAndFlush(secondUser);
    usersDao.saveAndFlush(firstUser);

    TweetDto tweetDto =
        TweetFixture.getDefaultDtoInstance()
            .toBuilder()
            .id(null)
            .senderId(firstUser.getId())
            .build();

    Tweet tweet = TweetFixture.getDefaultModelInstance();
    tweet.setSenderId(firstUser.getId());

    tweetsDal.persistTweet(tweetDto);

    List<Tweet> persistedTweets = tweetsDao.findAll();

    Assertions.assertEquals(Collections.singletonList(tweet), persistedTweets);

    List<TweetDto> expectedTimeline =
        persistedTweets.stream().map(TweetMapper::fromModel).collect(Collectors.toList());

    List<TweetDto> secondUserTimeline = tweetsDal.getTimelineForUser(secondUser.getId(), 1L);

    Assertions.assertEquals(expectedTimeline, secondUserTimeline);
  }
}
