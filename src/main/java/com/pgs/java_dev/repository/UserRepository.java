package com.pgs.java_dev.repository;

import com.pgs.java_dev.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
