package com.georgeoliveira.tweets.api.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.api.services.TweetsService;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@MicronautTest
public class TweetsControllerTest {
  private static final String REQUEST_PATH = "/tweets";

  @Inject TweetsService tweetsService;

  @Inject
  @Client("${http.server.url}")
  RxHttpClient client;

  @Test
  void shouldReturnBadRequestWhenPostTweetRequestHasInvalidSenderId() {
    HttpClientResponseException httpClientResponseException =
        Assertions.assertThrows(
            HttpClientResponseException.class,
            () -> {
              PostTweetRequestDto postTweetRequestDto =
                  TweetFixture.getDefaultPostTweetRequestDtoInstance()
                      .toBuilder()
                      .senderId(null)
                      .build();
              client
                  .exchange(HttpRequest.POST(REQUEST_PATH, postTweetRequestDto), Long.class)
                  .blockingFirst();
            });
    Assertions.assertEquals(
        HttpStatus.BAD_REQUEST, httpClientResponseException.getResponse().getStatus());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "\n", "\t"})
  void shouldReturnBadRequestWhenPostTweetRequestHasInvalidText(String text) {
    HttpClientResponseException httpClientResponseException =
        Assertions.assertThrows(
            HttpClientResponseException.class,
            () -> {
              PostTweetRequestDto postTweetRequestDto =
                  TweetFixture.getDefaultPostTweetRequestDtoInstance()
                      .toBuilder()
                      .text(text)
                      .build();
              client
                  .exchange(HttpRequest.POST(REQUEST_PATH, postTweetRequestDto), Long.class)
                  .blockingFirst();
            });
    Assertions.assertEquals(
        HttpStatus.BAD_REQUEST, httpClientResponseException.getResponse().getStatus());
  }

  @ParameterizedTest
  @NullSource
  @ValueSource(longs = {-1, 0})
  void shouldReturnBadRequestWhenPostTweetRequestHasInvalidTimestamp(Long timestamp) {
    HttpClientResponseException httpClientResponseException =
        Assertions.assertThrows(
            HttpClientResponseException.class,
            () -> {
              PostTweetRequestDto postTweetRequestDto =
                  TweetFixture.getDefaultPostTweetRequestDtoInstance()
                      .toBuilder()
                      .timestamp(timestamp)
                      .build();
              client
                  .exchange(HttpRequest.POST(REQUEST_PATH, postTweetRequestDto), Long.class)
                  .blockingFirst();
            });
    Assertions.assertEquals(
        HttpStatus.BAD_REQUEST, httpClientResponseException.getResponse().getStatus());
  }

  @Test
  void shouldReturnTweetIdWhenPostTweetRequestIsValid() {
    TweetDto dto = TweetFixture.getDefaultDtoInstance();
    when(tweetsService.publishTweet(any())).thenReturn(dto);

    PostTweetRequestDto postTweetRequestDto = TweetFixture.getDefaultPostTweetRequestDtoInstance();

    HttpResponse response =
        client
            .exchange(HttpRequest.POST(REQUEST_PATH, postTweetRequestDto), Long.class)
            .blockingFirst();

    Assertions.assertEquals(HttpStatus.CREATED, response.getStatus());
    Assertions.assertEquals(dto.getId(), response.body());
  }

  @MockBean(TweetsService.class)
  TweetsService tweetsService() {
    return mock(TweetsService.class);
  }
}
