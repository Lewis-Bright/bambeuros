package com.lewisb.bambeuro.jpa;

import com.lewisb.bambeuro.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
