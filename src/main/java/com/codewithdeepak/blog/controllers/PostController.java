package com.codewithdeepak.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdeepak.blog.config.AppConstants;
import com.codewithdeepak.blog.payloads.ApiResponse;

import com.codewithdeepak.blog.payloads.PostDto;
import com.codewithdeepak.blog.payloads.PostResponse;
import com.codewithdeepak.blog.services.FileService;
import com.codewithdeepak.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value(value = "${project.image}")
	private String path;

	// CREATE POST
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @Valid @PathVariable Integer userId,
			@Valid @PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
//		System.out.println("Date ===>> "+createPost.getDate());
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// GET BY USER
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> posts = this.postService.getPostByUser(userId);
		System.out.println("posts ==<>> " + posts);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// GET POST BY Category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> posts = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}

	// Get All post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost() {
		List<PostDto> allPost = this.postService.getAllPost();
		return new ResponseEntity<List<PostDto>>(allPost, HttpStatus.OK);
	}

	// Get All post with pagination
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPostsWithPagination(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse allPostWithPagination = this.postService.getAllPostWithPagination(pageNumber, pageSize, sortBy,
				sortDir);
		return new ResponseEntity<PostResponse>(allPostWithPagination, HttpStatus.OK);
	}

	// Get post by Post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	// delete post
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePostById(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post is successfully deleted!!", true);

	}

	// update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// search post by title
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}

	// search post by title using @Query annotation
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitleUsingQueryAnnotation(@PathVariable String keywords) {
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result, HttpStatus.OK);

	}

	// Upload image
//	@RequestMapping(value = "/post/image/upload/{postId}", method = RequestMethod.POST, 
//            consumes ="multipart/form-data" , 
//            produces = { "application/json", "application/xml" })
	@PostMapping("/post/image/upload/{postId}")

	public ResponseEntity<PostDto> uploadPostImage(@RequestPart   MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		// @RequestParam("image") MultipartFile image, here image is used for sending
		// parameter from postman
		PostDto postDto = this.postService.getPostById(postId);

		String fileName = this.fileService.uploadImage(path, image);

		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}
	
	
	//Serve or view file 
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
//		System.out.println("view ==>> "+response.getOutputStream());
//		System.out.println("view image file name from local dir ==>> "+imageName);
	}
	
}
