package com.somniuss.oracle.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, unique = true)
	private String username;

	@Column(length = 100)
	private String password;

	@Email(message = "Invalid email format")
	@Column(length = 255, unique = true)
	private String email;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role = UserRole.USER;

	@Pattern(regexp = "\\d+", message = "Telegram ID must contain only digits")
	@Column(name = "telegram_id", unique = true, length = 30)
	private String telegramId;

	@Column(name = "subscribed")
	private boolean subscribed;

	@Enumerated(EnumType.STRING)
	@Column(name = "subscription_type")
	private SubscriptionType subscriptionType;

	@Column(name = "subscription_expiry")
	private LocalDate subscriptionExpiry;

	@Column(name = "last_prediction")
	private LocalDateTime lastPrediction;

	@Column(name = "is_banned")
	private boolean isBanned;

	public User() {
	}

	public User(String username, String password, String email, UserRole role) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role != null ? role : UserRole.USER;
	}

	public User(String telegramId, String username) {
		this.telegramId = telegramId;
		this.username = username;
		this.role = UserRole.USER;
	}

	public User(String username, String password, String email, UserRole role, String telegramId, boolean subscribed,
			SubscriptionType subscriptionType, LocalDate subscriptionExpiry, LocalDateTime lastPrediction,
			boolean isBanned) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role != null ? role : UserRole.USER;
		this.telegramId = telegramId;
		this.subscribed = subscribed;
		this.subscriptionType = subscriptionType;
		this.subscriptionExpiry = subscriptionExpiry;
		this.lastPrediction = lastPrediction;
		this.isBanned = isBanned;
	}

	@PrePersist
	public void prePersist() {
		if (this.role == null) {
			this.role = UserRole.USER;
		}
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getTelegramId() {
		return telegramId;
	}

	public void setTelegramId(String telegramId) {
		this.telegramId = telegramId;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	public LocalDate getSubscriptionExpiry() {
		return subscriptionExpiry;
	}

	public void setSubscriptionExpiry(LocalDate subscriptionExpiry) {
		this.subscriptionExpiry = subscriptionExpiry;
	}

	public LocalDateTime getLastPrediction() {
		return lastPrediction;
	}

	public void setLastPrediction(LocalDateTime lastPrediction) {
		this.lastPrediction = lastPrediction;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean banned) {
		isBanned = banned;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof User user))
			return false;
		return id != null && id.equals(user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", email='" + email + '\'' + ", role=" + role
				+ ", telegramId='" + telegramId + '\'' + ", subscribed=" + subscribed + ", subscriptionType="
				+ subscriptionType + ", subscriptionExpiry=" + subscriptionExpiry + ", lastPrediction=" + lastPrediction
				+ ", isBanned=" + isBanned + '}';
	}
}
