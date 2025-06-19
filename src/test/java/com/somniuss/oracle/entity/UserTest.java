package com.somniuss.oracle.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

	@Test
	void testEqualsSameId() {
		User user1 = new User();
		user1.setId(1L);
		user1.setUsername("andrei");

		User user2 = new User();
		user2.setId(1L);
		user2.setUsername("valeria");

		assertEquals(user1, user2);
		assertEquals(user1.hashCode(), user2.hashCode());
	}

	@Test
	void testNotEqualsDifferentId() {
		User user1 = new User();
		user1.setId(1L);

		User user2 = new User();
		user2.setId(2L);

		assertNotEquals(user1, user2);
		assertNotEquals(user1.hashCode(), user2.hashCode());
	}

	@Test
	void testEqualsNullId() {
		User user1 = new User();
		User user2 = new User();

		assertNotEquals(user1, user2);
		assertEquals(user1, user1);
	}
}
