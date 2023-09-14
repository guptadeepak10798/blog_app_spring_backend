package com.codewithdeepak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdeepak.blog.entities.Category;

/*@Service should not be here*/
public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
