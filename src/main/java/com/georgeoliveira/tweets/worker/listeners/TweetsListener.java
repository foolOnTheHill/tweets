package com.georgeoliveira.tweets.worker.listeners;

import com.georgeoliveira.events.Key;
import com.georgeoliveira.events.Value;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.mappers.TweetMapper;
import com.georgeoliveira.tweets.worker.processors.TweetsProcessor;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import java.io.IOException;
import javax.inject.Inject;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class TweetsListener {
  @Inject TweetsProcessor tweetsProcessor;

  @Topic("${topics.tweets}")
  void listen(ConsumerRecord<Key, Value> event) throws IOException {
    TweetDto tweetDto = TweetMapper.fromRecord(event);
    tweetsProcessor.accept(tweetDto);
  }
}
