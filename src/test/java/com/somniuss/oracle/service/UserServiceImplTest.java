package com.somniuss.oracle.service;

import com.somniuss.oracle.entity.User;
import com.somniuss.oracle.entity.UserRole;
import com.somniuss.oracle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private UserServiceImpl userService;

	@BeforeEach
	void setUp() {
		userRepository = mock(UserRepository.class);
		passwordEncoder = mock(PasswordEncoder.class);
		userService = new UserServiceImpl(userRepository, passwordEncoder);
	}

	@Test
	void saveUser_ShouldSetRoleEncodePasswordAndSave() {
		User user = new User();
		user.setPassword("plainPassword");

		when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");

		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		User savedUser = userService.saveUser(user);

		assertEquals(UserRole.USER, savedUser.getRole());

		assertEquals("encodedPassword", savedUser.getPassword());

		verify(userRepository, times(1)).save(savedUser);
	}
}
