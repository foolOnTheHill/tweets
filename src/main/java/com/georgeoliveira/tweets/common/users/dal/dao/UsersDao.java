package com.georgeoliveira.tweets.common.users.dal.dao;

import com.georgeoliveira.tweets.common.users.models.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface UsersDao extends JpaRepository<User, Long> {}
