package com.burst.sp1.repository;

import com.burst.sp1.model.Role;

public interface RoleDao {

    Role getById(int id);
    Role getByRole(String role);
}
