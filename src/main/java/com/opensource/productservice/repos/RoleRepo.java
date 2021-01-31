package com.opensource.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opensource.productservice.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long> {

}
