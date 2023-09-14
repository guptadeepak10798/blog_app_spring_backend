package com.codewithdeepak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithdeepak.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
