package com.georgeoliveira.tweets.api.controllers;

import com.georgeoliveira.tweets.api.services.TimelinesService;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import java.util.List;
import javax.inject.Inject;

@Controller("/timelines")
public class TimelinesController {
  @Inject TimelinesService timelinesService;

  @Get("/{userId}")
  HttpResponse<List<TweetDto>> getUserTimeline(@PathVariable Long userId) {
    List<TweetDto> tweetDtoList = timelinesService.getUserTimelineTweets(userId);

    if (tweetDtoList.isEmpty()) {
      return HttpResponse.noContent();
    }

    return HttpResponse.status(HttpStatus.FOUND).body(tweetDtoList);
  }
}
