package com.georgeoliveira.tweets.common.tweets.outbox.dal;

import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.outbox.dal.dao.TweetsOutboxDao;
import com.georgeoliveira.tweets.common.tweets.outbox.dtos.EventType;
import com.georgeoliveira.tweets.common.tweets.outbox.mappers.TweetOutboxMapper;
import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TweetsOutboxDal {
  @Inject TweetsOutboxDao tweetsOutboxDao;

  public void sendToOutbox(TweetDto tweetDto, EventType eventType) {
    TweetOutbox tweetOutbox = TweetOutboxMapper.toOutboxModel(tweetDto, eventType);
    tweetsOutboxDao.save(tweetOutbox);
  }
}
