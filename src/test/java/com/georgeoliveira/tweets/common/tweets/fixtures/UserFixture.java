package com.georgeoliveira.tweets.common.tweets.fixtures;

import com.georgeoliveira.tweets.common.tweets.models.User;

public class UserFixture {
  public static final String DEFAULT_USERNAME = "cool_nickname";
  public static final Long DEFAULT_USER_ID = 14L;

  public static User getDefaultUserInstance() {
    User user = new User();
    user.setId(DEFAULT_USER_ID);
    user.setUsername(DEFAULT_USERNAME);
    return user;
  }
}
