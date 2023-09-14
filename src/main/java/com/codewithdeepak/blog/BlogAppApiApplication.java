package com.codewithdeepak.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.codewithdeepak.blog.config.AppConstants;
import com.codewithdeepak.blog.entities.Role;
import com.codewithdeepak.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Encoded password ===>  " + this.passwordEncoder.encode("nuc1234"));
		
//		try {
//			Role role = new Role();
//			role.setId(AppConstants.ADMIN_USER);
//			role.setName("ROLE_ADMIN");
//			
//			Role role1 = new Role();
//			role1.setId(AppConstants.NORMAL_USER);
//			role1.setName("ROLE_NORMAL");
//			
//			List<Role> roles = List.of(role,role1);
//			
//			List<Role> result = this.roleRepo.saveAll(roles);
//			
//			result.forEach(
//					r->{System.out.println(r.getName());}
//					);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
