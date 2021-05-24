package com.georgeoliveira.tweets.api.controllers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.georgeoliveira.tweets.api.services.TimelinesService;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import com.georgeoliveira.tweets.common.tweets.fixtures.TweetFixture;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@MicronautTest
public class TimelinesControllerTest {
  private static final String REQUEST_PATH = "/timelines";

  @Inject TimelinesService timelinesService;

  @Inject
  @Client("${http.server.url}")
  RxHttpClient client;

  @Test
  void shouldReturnEmptyResponseWhenTimelineIsNotFound() {
    HttpResponse<List> response =
        client
            .exchange(
                HttpRequest.GET(String.format("%s/%d", REQUEST_PATH, UserFixture.DEFAULT_USER_ID)),
                List.class)
            .blockingFirst();

    Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    Assertions.assertNull(response.body());
  }

  @Test
  void shouldRetrieveUserTimelineTweets() {
    List<TweetDto> expectedDtoList =
        Collections.singletonList(TweetFixture.getDefaultDtoInstance());
    when(timelinesService.getUserTimelineTweets(anyLong())).thenReturn(expectedDtoList);

    HttpResponse<List> response =
        client
            .exchange(
                HttpRequest.GET(String.format("%s/%d", REQUEST_PATH, UserFixture.DEFAULT_USER_ID)),
                List.class)
            .blockingFirst();

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());

    List<TweetDto> retrievedTweets =
        objectMapper.convertValue(response.body(), new TypeReference<List<TweetDto>>() {});

    Assertions.assertEquals(HttpStatus.FOUND, response.getStatus());
    Assertions.assertEquals(expectedDtoList, retrievedTweets);
  }

  @MockBean(TimelinesService.class)
  TimelinesService timelinesService() {
    return mock(TimelinesService.class);
  }
}
