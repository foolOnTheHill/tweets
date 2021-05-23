package com.georgeoliveira.tweets.common.tweets.dal.dao;

import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface TweetsDao extends JpaRepository<Tweet, Long> {
  @Query(
      value =
          "SELECT * FROM tweets t JOIN follows f ON f.followee_id = t.sender_id WHERE f.follower_id = :userId ORDER BY timestamp DESC LIMIT :limit",
      nativeQuery = true)
  List<Tweet> findTimelineTweetsByUserId(Long userId, Long limit);
}
