package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com","ramesh","USER","9876543210");
        assertNotNull(user);
        assertEquals(1L, user.getUserId());
        assertEquals("Ramesh Kumar", user.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", user.getEmail());
        assertEquals("ramesh", user.getPassword());
        assertEquals("USER", user.getUserRole());
        assertEquals("9876543210", user.getPhoneNumber());
    }

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setUserId(1L);
        user.setName("Ramesh Kumar");
        user.setEmail("ramesh.kumar@nucleusteq.com");
        user.setPassword("ramesh");
        user.setPhoneNumber("9876543210");
        user.setUserRole("USER");

        assertEquals(1L, user.getUserId());
        assertEquals("Ramesh Kumar", user.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", user.getEmail());
        assertEquals("ramesh", user.getPassword());
        assertEquals("USER", user.getUserRole());
        assertEquals("9876543210", user.getPhoneNumber());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com", "ramesh", "USER", "9876543210");
        User user2 = new User(1L, "Mahesh Kumar", "mahesh.kumar@nucleusteq.com", "mahesh", "USER", "8919293940");
        User user3 = new User(2L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com", "ramesh", "USER", "9876543210");

        assertNotEquals(user1, user2);
        assertNotEquals(user1, user3);
        
        assertNotEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    public void testToString() {
        User user = new User(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com", "ramesh", "USER", "9876543210");
        String expectedToString = String.format("User(userId=%d, name=%s, email=%s, password=%s, userRole=%s, phoneNumber=%s)",
                user.getUserId(), user.getName(), user.getEmail(), user.getPassword(), user.getUserRole(), user.getPhoneNumber());
        assertEquals(expectedToString, user.toString());
    }
}
