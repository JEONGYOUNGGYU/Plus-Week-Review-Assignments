package com.sparta.plusweekreviewassignment.repository;

import com.sparta.plusweekreviewassignment.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String userName);

  User findByPassword(String password);
}
