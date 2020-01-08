package com.burst.sp1.repository;

import com.burst.sp1.model.User;

import java.util.List;

public interface UserDao {


    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    User getUserByName(String name);

    User getUserById(int id);

    List<User> findAll();

    void dropTable();

    void createTable();
}
