package com.burst.sp1.service;

import com.burst.sp1.model.Role;
import com.burst.sp1.model.User;

import java.util.List;

public interface UserService {

    boolean addUser(User user);

    boolean addUserRoot(User user, String role);

    void editUser(User user, String s);

    void deleteUser(User user);

    User getUserByName(String name);

    User getUserById(int id);

    List<User> getAllUsers();

    void dropTable();

    void createTable();

    boolean isExist(String name);

    boolean validUser(String name, String password);

    void addRoleUser(User user);

    void addRoleAdmin(User user);

    Role getRoleById(int id);

    void initRole(User user, String access);

    void deleteById(int id);
}