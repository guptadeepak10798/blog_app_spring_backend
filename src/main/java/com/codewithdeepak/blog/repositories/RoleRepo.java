package com.codewithdeepak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdeepak.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
