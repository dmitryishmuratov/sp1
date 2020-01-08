package com.burst.sp1.repository;

import com.burst.sp1.model.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        User userDel = entityManager.find(User.class, user.getId());
        entityManager.remove(userDel);
    }

    @Override
    public User getUserByName(String username) {
        try {
            Query<User> query = (Query<User>) entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username");
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User u", User.class).getResultList();
    }

    @Override
    public void dropTable() {
        entityManager.createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public void createTable() {}
}