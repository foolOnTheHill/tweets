package com.georgeoliveira.tweets.common.users.fixtures;

import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.models.User;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserFixture {
  public static final String DEFAULT_USERNAME = "cool_nickname";
  public static final Long DEFAULT_USER_ID = 1L;

  public static User getDefaultUserInstance() {
    User user = new User();
    user.setId(DEFAULT_USER_ID);
    user.setUsername(DEFAULT_USERNAME);
    return user;
  }

  public static UserDto getDefaultDtoInstance() {
    return UserDto.builder()
        .id(DEFAULT_USER_ID)
        .username(DEFAULT_USERNAME)
        .followers(Collections.emptyList())
        .build();
  }

  public static List<User> getUsersSequence(int size) {
    return IntStream.rangeClosed(1, size)
        .mapToObj(
            i -> {
              User user = new User();
              user.setId(DEFAULT_USER_ID + i);
              user.setUsername(DEFAULT_USERNAME + "_" + i);
              return user;
            })
        .collect(Collectors.toList());
  }
}
