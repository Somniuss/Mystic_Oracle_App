package com.somniuss.oracle.repository;

import com.somniuss.oracle.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByTelegramId(String telegramId);

	Optional<User> findByEmail(String email);
}
