package com.burst.sp1.repository;

import com.burst.sp1.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManager em;

    @Autowired
    public RoleDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Role getById(int id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role getByRole(String role) {
        Query query = em.createQuery("SELECT r FROM Role r WHERE r.role = :role", Role.class);
        query.setParameter("role", role);
        return (Role) query.getSingleResult();
    }
}
