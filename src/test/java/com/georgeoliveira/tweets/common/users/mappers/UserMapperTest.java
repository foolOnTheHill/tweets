package com.georgeoliveira.tweets.common.users.mappers;

import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.users.models.User;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserMapperTest {
  @Test
  void shouldMapDefaultFromModelCorrectly() {
    User user = UserFixture.getDefaultUserInstance();
    UserDto userDto = UserMapper.fromModel(user);
    UserDto expectedUserDto = UserFixture.getDefaultDtoInstance();
    Assertions.assertEquals(userDto, expectedUserDto);
  }

  @Test
  void shouldMapFollowersFromModelCorrectly() {
    List<User> users = UserFixture.getUsersSequence(2);

    User firstUser = users.get(0);
    User secondUser = users.get(1);

    firstUser.setFollowers(Collections.singletonList(secondUser));

    UserDto firstUserDto = UserMapper.fromModel(firstUser);
    UserDto secondUserDto = UserMapper.fromModel(secondUser);

    Assertions.assertEquals(Collections.singletonList(secondUserDto), firstUserDto.getFollowers());
  }
}
