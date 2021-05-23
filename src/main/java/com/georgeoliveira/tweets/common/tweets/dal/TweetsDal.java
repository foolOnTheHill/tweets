package com.georgeoliveira.tweets.common.tweets.dal;

import com.georgeoliveira.tweets.common.tweets.dal.dao.TweetsDao;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.mappers.TweetMapper;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class TweetsDal {
  @Inject TweetsDao tweetsDao;

  @Transactional
  public TweetDto persistTweet(TweetDto tweetDto) {
    Tweet tweet = TweetMapper.fromDto(tweetDto);
    Tweet persistedTweet = tweetsDao.save(tweet);
    return TweetMapper.fromModel(persistedTweet);
  }

  public List<TweetDto> getTimelineForUser(Long userId, Long timelineSize) {
    return tweetsDao
        .findTimelineTweetsByUserId(userId, timelineSize)
        .stream()
        .map(TweetMapper::fromModel)
        .collect(Collectors.toList());
  }
}
