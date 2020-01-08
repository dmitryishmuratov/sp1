package com.burst.sp1.service;

import com.burst.sp1.model.Role;
import com.burst.sp1.model.User;
import com.burst.sp1.repository.RoleDao;
import com.burst.sp1.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    private Role roleUser;
    private Role roleAdmin;

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplement(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean addUser(User user) {
        if (!isExist(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            addRoleUser(user);
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean addUserRoot(User user, String role) {
        if (!isExist(user.getUsername())) {
            initRole(user, role);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void editUser(User user, String role) {
        initRole(user, role);
        userDao.editUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }


    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void dropTable() {
        userDao.dropTable();
    }

    @Override
    @Transactional
    public void createTable() {
    }

    @Override
    @Transactional
    public boolean isExist(String name) {
        return getUserByName(name) != null;
    }

    @Override
    @Transactional
    public boolean validUser(String name, String password) {
        User user = getUserByName(name);
        return user.getPassword().equals(password);
    }

    @Override
    @Transactional
    public void addRoleUser(User user) {
        if (roleUser == null) {
            roleUser = roleDao.getById(1);
        }
        user.getRoles().clear();
        user.addRole(roleUser);
    }

    @Override
    @Transactional
    public void addRoleAdmin(User user) {

        if (roleAdmin == null) {
            roleAdmin = roleDao.getById(2);
        }
        user.getRoles().clear();
        user.addRole(roleAdmin);
    }

    @Override
    @Transactional
    public Role getRoleById(int id) {
        Role role = roleDao.getById(id);
        return role;
    }

    @Override
    @Transactional
    public void initRole(User user, String access) {
        if (access.equals("Admin")) {
            addRoleAdmin(user);
        } else {
            addRoleUser(user);
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        deleteUser(getUserById(id));
    }
}
