package com.lewisb.bambeuro.jpa;

import com.lewisb.bambeuro.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(("SELECT user FROM User user WHERE user.name = :name"))
    Optional<User> findByName(@Param("name") String name);

}
