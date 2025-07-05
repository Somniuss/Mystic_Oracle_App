package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.SubscriptionType;
import com.somniuss.oracle.entity.User;
import com.somniuss.oracle.entity.UserRole;
import com.somniuss.oracle.exception.UserNotFoundException;
import com.somniuss.oracle.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User saveUser(User user) {
		if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		if (user.getRole() == null) {
			user.setRole(UserRole.USER);
		}
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteUserById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User with id " + id + " not found");
		}
		userRepository.deleteById(id);
	}

	@Override
	public User updateTelegramId(Long userId, String telegramId) {
		User user = getUserOrThrow(userId);
		user.setTelegramId(telegramId);
		return userRepository.save(user);
	}

	@Override
	public User updateSubscription(Long userId, boolean isSubscribed, SubscriptionType subscriptionType,
			LocalDate expiryDate) {
		User user = getUserOrThrow(userId);
		user.setSubscribed(isSubscribed);
		user.setSubscriptionType(subscriptionType);
		user.setSubscriptionExpiry(expiryDate);
		return userRepository.save(user);
	}

	@Override
	public User updateLastPrediction(Long userId, LocalDateTime lastPrediction) {
		User user = getUserOrThrow(userId);
		user.setLastPrediction(lastPrediction);
		return userRepository.save(user);
	}

	@Override
	public User updateBanStatus(Long userId, boolean isBanned) {
		User user = getUserOrThrow(userId);
		user.setBanned(isBanned);
		return userRepository.save(user);
	}

	private User getUserOrThrow(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
	}
}
