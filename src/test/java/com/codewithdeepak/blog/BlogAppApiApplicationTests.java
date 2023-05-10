package com.codewithdeepak.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithdeepak.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApiApplicationTests {

	private UserRepo userRepo;
	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		
		String className = userRepo.getClass().getName();
		String packageName = userRepo.getClass().getPackageName();
		System.out.println("className : "+className);
		System.out.println("packageName : "+packageName);
	}
}
