package ru.kata.spring.boot_security.demo.service;


import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    void saveUpdateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    List<Role> getAllRoles();

    Set<Role> findAllById(List<Long> roles);

}
