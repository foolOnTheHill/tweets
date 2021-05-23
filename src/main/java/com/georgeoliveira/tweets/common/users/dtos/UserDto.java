package com.georgeoliveira.tweets.common.users.dtos;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class UserDto {
  Long id;
  String username;
  List<UserDto> followers;
}
