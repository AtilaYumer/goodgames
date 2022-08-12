package com.konak.goodgames.repository;

import com.konak.goodgames.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByOrderByIdAsc();
  Optional<User> findByEmail(String username);
}
