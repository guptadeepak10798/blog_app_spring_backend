package com.codewithdeepak.blog.services;

import java.util.List;

import com.codewithdeepak.blog.payloads.PostDto;
import com.codewithdeepak.blog.payloads.PostResponse;

public interface PostService {

	//Create post
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//Update post
	
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//Delete post
	
	void deletePost(Integer postId);
	
	//get All posts
	
	List<PostDto> getAllPost();
	
	//get All post with pagination
	PostResponse getAllPostWithPagination(Integer pageNumber, Integer pageSize,String sortBy, String sortDir);
	
	//get post by Id
	
	PostDto getPostById(Integer postId);
	
	//get All post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	// Get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	// search post by Keyword
	List<PostDto> searchPosts(String keyword);

	// search post by Keyword query
	List<PostDto> searchPostsUsingQuery(String keyword);
	
	
	
}
