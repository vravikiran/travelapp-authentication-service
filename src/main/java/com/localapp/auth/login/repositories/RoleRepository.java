package com.localapp.auth.login.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localapp.auth.login.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
