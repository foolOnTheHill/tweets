package com.georgeoliveira.tweets.api.services;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.api.mappers.TweetRequestMapper;
import com.georgeoliveira.tweets.common.tweets.dal.TweetsDal;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.outbox.dal.TweetsOutboxDal;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetsService {
  @Inject TweetsDal tweetsDal;

  @Inject TweetsOutboxDal tweetsOutboxDal;

  public TweetDto publishTweet(PostTweetRequestDto request) {
    TweetDto tweetDto = TweetRequestMapper.fromPostRequest(request);
    TweetDto persistedTweetDto = tweetsDal.persistTweet(tweetDto);

    tweetsOutboxDal.sendToOutbox(persistedTweetDto, EventType.PUBLISH_TWEET);

    return persistedTweetDto;
  }
}
