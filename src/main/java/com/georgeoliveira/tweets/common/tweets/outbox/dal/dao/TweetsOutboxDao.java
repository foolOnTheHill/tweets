package com.georgeoliveira.tweets.common.tweets.outbox.dal.dao;

import com.georgeoliveira.tweets.common.tweets.outbox.models.TweetOutbox;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface TweetsOutboxDao extends JpaRepository<TweetOutbox, UUID> {}
