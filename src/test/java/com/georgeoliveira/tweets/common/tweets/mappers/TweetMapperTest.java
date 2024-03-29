package com.georgeoliveira.tweets.common.tweets.mappers;

import com.georgeoliveira.events.Key;
import com.georgeoliveira.events.Value;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import java.io.IOException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetMapperTest {
  @Test
  void shouldMapCorrectlyFromDto() {
    TweetDto tweetDto = TweetFixture.getDefaultDtoInstance();
    Tweet tweet = TweetMapper.fromDto(tweetDto);
    Tweet expectedTweet = TweetFixture.getDefaultModelInstance();

    Assertions.assertEquals(expectedTweet, tweet);
  }

  @Test
  void shouldMapCorrectlyFromModel() {
    Tweet tweet = TweetFixture.getDefaultModelInstance();
    TweetDto tweetDto = TweetMapper.fromModel(tweet);
    TweetDto expectedDto = TweetFixture.getDefaultDtoInstance();

    Assertions.assertEquals(expectedDto, tweetDto);
  }

  @Test
  void shouldMapCorrectlyFromConsumerRecord() throws IOException {
    ConsumerRecord<Key, Value> consumerRecord = TweetFixture.getDefaultConsumerRecordInstance();
    TweetDto dto = TweetMapper.fromRecord(consumerRecord);
    TweetDto expectedDto = TweetFixture.getDefaultDtoInstance();

    Assertions.assertEquals(expectedDto, dto);
  }
}
