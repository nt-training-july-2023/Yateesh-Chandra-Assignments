package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

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
    public void testUserResponsesGetterAndSetter() {
        User user = new User();
        List<UserResponses> userResponses = new ArrayList<>();
        userResponses.add(new UserResponses(4L,5, 4, 20, 16, "2023-09-20"));
        userResponses.add(new UserResponses(5L,5, 3, 20, 12, "2023-09-19"));
        user.setUserResponses(userResponses);
        List<UserResponses> list = user.getUserResponses();
        assertNotSame(userResponses, list);
        assertEquals(userResponses, list);
    }

    @Test
    public void testEquals_NullObject() {
        User user = new User(5L, "Yateesh");
        assertFalse(user.equals(null));
    }

    @Test
    public void testEquals_SameInstance() {
        User user = new User(5L, "Yateesh");
        assertTrue(user.equals(user));
    }

    @Test
    public void testEquals_DifferentClass() {
        User user = new User(5L, "Yateesh");
        assertFalse(user.equals("Not a User Object"));
    }
    

    @Test
    void testEquals_SameProperties() {
        User user1 = new User(5L, "Yateesh");
        User user2 = new User(5L, "Yateesh");
        assertTrue(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentUserId() {
        User user1 = new User(5L, "Yateesh");
        User user2 = new User(6L, "Yateesh");
        assertFalse(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentName() {
        User user1 = new User(5L, "Krishna");
        User user2 = new User(5L, "Yateesh");
        assertFalse(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentEmail() {
        User user1 = new User(5L, "Yateesh");
        user1.setEmail("yateesh@domain.com");
        User user2 = new User(5L, "Yateesh");
        user2.setEmail("chandra@domain.com");
        assertFalse(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentPassword() {
        User user1 = new User(5L, "Yateesh");
        user1.setPassword("password1");
        User user2 = new User(5L, "Yateesh");
        user2.setPassword("password2");
        assertFalse(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentUserRole() {
        User user1 = new User(5L, "Yateesh");
        user1.setUserRole("ADMIN");
        User user2 = new User(5L, "Yateesh");
        user2.setUserRole("USER");
        assertFalse(user1.equals(user2));
    }

    @Test
    void testEquals_DifferentPhoneNumber() {
        User user1 = new User(5L, "Yateesh");
        user1.setPhoneNumber("9876543210");
        User user2 = new User(5L, "Yateesh");
        user2.setPhoneNumber("9865432170");
        assertFalse(user1.equals(user2));
    }

}
