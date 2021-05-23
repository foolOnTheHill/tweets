package com.georgeoliveira.tweets.common.users.dal;

import com.georgeoliveira.tweets.common.users.dal.dao.UsersDao;
import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.mappers.UserMapper;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UsersDal {
  @Inject UsersDao usersDao;

  public List<UserDto> getUserFollowers(Long userId) {
    return usersDao
        .findById(userId)
        .map(UserMapper::fromModel)
        .map(UserDto::getFollowers)
        .orElse(Collections.emptyList());
  }
}
