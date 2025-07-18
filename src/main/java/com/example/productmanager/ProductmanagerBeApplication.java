package com.example.productmanager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.productmanager.model.AppRole;
import com.example.productmanager.model.AppUser;
import com.example.productmanager.repository.AppRoleRepository;
import com.example.productmanager.repository.AppUserRepository;

@SpringBootApplication
public class ProductmanagerBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductmanagerBeApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner(AppUserRepository userRepo, PasswordEncoder encoder, AppRoleRepository roleRepo) {
	  return args -> {
		// Vérifier si le rôle USER existe déjà
		  AppRole userRole1 = roleRepo.findByRoleName("USER");
		  if (userRole1 == null) {
		      userRole1 = new AppRole();
		      userRole1.setRoleName("USER");
		      roleRepo.save(userRole1);
		  }

		  // Vérifier si l’utilisateur user existe déjà
		  if (!userRepo.existsByUsername("user")) {
		      AppUser user1 = new AppUser();
		      user1.setUsername("user");
		      user1.setPassword(encoder.encode("user123"));
		      user1.getRoles().add(userRole1);
		      userRepo.save(user1);
		  }

		  // Vérifier si le rôle ADMIN existe déjà
		  AppRole userRole2 = roleRepo.findByRoleName("ADMIN");
		  if (userRole2 == null) {
		      userRole2 = new AppRole();
		      userRole2.setRoleName("ADMIN");
		      roleRepo.save(userRole2);
		  }

		  // Vérifier si l’utilisateur admin existe déjà
		  if (!userRepo.existsByUsername("admin")) {
		      AppUser user2 = new AppUser();
		      user2.setUsername("admin");
		      user2.setPassword(encoder.encode("admin123"));
		      user2.getRoles().add(userRole2);
		      user2.getRoles().add(userRole1);
		      userRepo.save(user2);
		  }
	    
	  };
	}

}
