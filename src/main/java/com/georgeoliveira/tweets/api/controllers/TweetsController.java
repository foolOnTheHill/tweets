package com.georgeoliveira.tweets.api.controllers;

import com.georgeoliveira.tweets.api.dtos.PostTweetRequestDto;
import com.georgeoliveira.tweets.api.services.TweetsService;
import com.georgeoliveira.tweets.common.tweets.dtos.TweetDto;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import java.util.Objects;
import javax.inject.Inject;
import javax.validation.Valid;

@Validated
@Controller("/tweets")
public class TweetsController {
  @Inject TweetsService tweetsService;

  @Post
  public HttpResponse<Long> postTweet(@Valid @Body PostTweetRequestDto request) {
    TweetDto tweet = tweetsService.publishTweet(request);

    if (Objects.nonNull(tweet)) {
      return HttpResponse.created(tweet.getId());
    }

    return HttpResponse.notFound();
  }
}
