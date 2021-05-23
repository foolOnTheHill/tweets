package com.georgeoliveira.tweets.worker.listeners;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import com.georgeoliveira.events.Key;
import com.georgeoliveira.events.Value;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.worker.processors.TweetsProcessor;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.time.Duration;
import javax.inject.Inject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TweetsListenerTest {
  @Inject TweetsProcessor tweetsProcessor;

  @Inject
  @KafkaClient("producer-test")
  Producer<Key, Value> producer;

  @io.micronaut.context.annotation.Value("${topics.tweets}")
  String tweetsTopic;

  @Test
  void shouldCallTweetsProcessorWhenEventIsConsumed() {
    ProducerRecord<Key, Value> producerRecord = buildDefaultProducerRecord();

    producer.send(producerRecord);

    verify(tweetsProcessor, timeout(Duration.ofSeconds(30))).accept(any(TweetDto.class));
  }

  private ProducerRecord<Key, Value> buildDefaultProducerRecord() {
    ConsumerRecord<Key, Value> consumerRecord = TweetFixture.getDefaultConsumerRecordInstance();
    return new ProducerRecord<>(tweetsTopic, consumerRecord.key(), consumerRecord.value());
  }

  @MockBean(TweetsProcessor.class)
  TweetsProcessor tweetsProcessor() {
    return mock(TweetsProcessor.class);
  }
}
