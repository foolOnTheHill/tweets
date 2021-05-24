package com.georgeoliveira.tweets.common.users.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(schema = "public", name = "users")
@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class User {
  @Id @EqualsAndHashCode.Exclude Long id;

  String username;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "follows",
      joinColumns = {@JoinColumn(name = "followee_id")},
      inverseJoinColumns = {@JoinColumn(name = "follower_id")})
  List<User> followers = new ArrayList<>();
}
