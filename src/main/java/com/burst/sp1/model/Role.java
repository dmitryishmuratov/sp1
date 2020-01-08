package com.burst.sp1.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String role;

    public Role() {
        this.role = "ROLE_USER";
    }

    public Role(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role1 = (Role) o;

        if (id != role1.id) return false;
        return role != null ? role.equals(role1.role) : role1.role == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getRole();
    }
}
