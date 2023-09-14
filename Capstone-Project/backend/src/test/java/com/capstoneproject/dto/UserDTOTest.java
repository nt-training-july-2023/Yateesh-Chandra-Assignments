package com.capstoneproject.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class UserDTOTest {

    @Test
    public void testUserDTOConstructor() {
        UserDTO userDto = new UserDTO(1L, "Ramesh Kumar", "ramesh.kumar@nucleusteq.com","ramesh","USER","9876543210");
        assertNotNull(userDto);
        assertEquals(1L, userDto.getId());
        assertEquals("Ramesh Kumar", userDto.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", userDto.getEmail());
        assertEquals("ramesh", userDto.getPassword());
        assertEquals("USER", userDto.getUserRole());
        assertEquals("9876543210", userDto.getPhoneNumber());
    }

    @Test
    public void testUserDTOGettersAndSetters() {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setName("Ramesh Kumar");
        userDto.setEmail("ramesh.kumar@nucleusteq.com");
        userDto.setPassword("ramesh");
        userDto.setPhoneNumber("9876543210");
        userDto.setUserRole("USER");

        assertEquals(1L, userDto.getId());
        assertEquals("Ramesh Kumar", userDto.getName());
        assertEquals("ramesh.kumar@nucleusteq.com", userDto.getEmail());
        assertEquals("ramesh", userDto.getPassword());
        assertEquals("USER", userDto.getUserRole());
        assertEquals("9876543210", userDto.getPhoneNumber());
    }

    @Test
    public void testDefaultConstructor() {
        Long id = 5L;
        UserDTO userDto = new UserDTO();
        userDto.setId(id);
        assertEquals(id, userDto.getId());
        assertEquals(null, userDto.getName());
        assertEquals(null, userDto.getEmail());
        assertEquals(null, userDto.getPassword());
        assertEquals(null, userDto.getUserRole());
        assertEquals(null, userDto.getPhoneNumber());
    }
    
    @Test
    public void testBuilderUserDTO() {
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .name("Yateesh Chandra")
                .email("yateeshchandraduggirala3@gmail.com")
                .password("password123")
                .userRole("USER")
                .phoneNumber("9876543210")
                .build();

        assertEquals(1L, userDTO.getId());
        assertEquals("Yateesh Chandra", userDTO.getName());
        assertEquals("yateeshchandraduggirala3@gmail.com", userDTO.getEmail());
        assertEquals("password123", userDTO.getPassword());
        assertEquals("USER", userDTO.getUserRole());
        assertEquals("9876543210", userDTO.getPhoneNumber());
    }
}
