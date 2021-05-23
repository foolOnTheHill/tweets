package com.georgeoliveira.tweets.common.users.dal;

import com.georgeoliveira.tweets.common.users.dal.dao.UsersDao;
import com.georgeoliveira.tweets.common.users.dtos.UserDto;
import com.georgeoliveira.tweets.common.users.fixtures.UserFixture;
import com.georgeoliveira.tweets.common.users.mappers.UserMapper;
import com.georgeoliveira.tweets.common.users.models.User;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@MicronautTest
public class UsersDalTest {
  @Inject UsersDao usersDao;

  @Inject UsersDal usersDal;

  List<User> users = UserFixture.getUsersSequence(2);

  @BeforeEach
  void setUpData() {
    User firstUser = users.get(0);
    User secondUser = users.get(1);

    firstUser.setFollowers(Collections.singletonList(secondUser));

    usersDao.save(secondUser);
    usersDao.save(firstUser);
  }

  @AfterEach
  void cleanData() {
    usersDao.deleteAll();
  }

  @Test
  void shouldGetUserWithFollowers() {
    Long firstUserId = users.get(0).getId();
    Long secondUserId = users.get(1).getId();

    List<UserDto> firstUserFollowers = usersDal.getUserFollowers(firstUserId);
    List<UserDto> secondUserFollowers = usersDal.getUserFollowers(secondUserId);

    Assertions.assertEquals(Collections.emptyList(), secondUserFollowers);
    Assertions.assertEquals(
        Collections.singletonList(UserMapper.fromModel(users.get(1))), firstUserFollowers);
  }
}
