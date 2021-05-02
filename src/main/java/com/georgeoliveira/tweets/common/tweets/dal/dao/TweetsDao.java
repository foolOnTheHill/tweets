package com.georgeoliveira.tweets.common.tweets.dal.dao;

import com.georgeoliveira.tweets.common.tweets.models.Tweet;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface TweetsDao extends JpaRepository<Tweet, Long> {}
