package com.georgeoliveira.tweets.api.services;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.api.mappers.TweetRequestMapper;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetsService {
  @Inject TweetsDal tweetsDal;

  public TweetDto publishTweet(PostTweetRequestDto request) {
    TweetDto tweetDto = TweetRequestMapper.fromPostRequest(request);
    TweetDto persistedTweetDto = tweetsDal.persistTweet(tweetDto);
    return persistedTweetDto;
  }
}
