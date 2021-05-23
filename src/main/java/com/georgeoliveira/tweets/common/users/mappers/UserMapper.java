package com.georgeoliveira.tweets.common.users.mappers;

import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.models.User;
import java.util.stream.Collectors;

public class UserMapper {
  public static UserDto fromModel(User user) {
    return UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        .followers(
            user.getFollowers().stream().map(UserMapper::fromModel).collect(Collectors.toList()))
        .build();
  }
}
