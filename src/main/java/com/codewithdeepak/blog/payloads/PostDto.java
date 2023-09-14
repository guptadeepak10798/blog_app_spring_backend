package com.codewithdeepak.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
public class PostDto {

	private Integer postId;
	
	@NotEmpty(message = "Please enter title of post !!")
	private String title;
	
	@NotEmpty(message = "Please enter content of post !!")
	private String content;
	
	private String imageName;
	
	private Date date;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
