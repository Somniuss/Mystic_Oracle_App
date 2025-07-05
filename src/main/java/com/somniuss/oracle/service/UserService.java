package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.SubscriptionType;
import com.somniuss.oracle.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);

    User updateTelegramId(Long userId, String telegramId);
    User updateSubscription(Long userId, boolean isSubscribed, SubscriptionType subscriptionType, LocalDate expiryDate);
    User updateLastPrediction(Long userId, LocalDateTime lastPrediction);
    User updateBanStatus(Long userId, boolean isBanned);
}
