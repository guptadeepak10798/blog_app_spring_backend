package com.codewithdeepak.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codewithdeepak.blog.entities.Category;
import com.codewithdeepak.blog.entities.Post;
import com.codewithdeepak.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer>{

	//Creating custom finder methods
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
	@Query("select p from Post p where p.title like :key")
	//%keyword%
	List<Post> searchByTitle(@Param("key") String title);
	
}
