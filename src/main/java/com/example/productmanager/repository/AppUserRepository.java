package com.example.productmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productmanager.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	  Optional<AppUser> findByUsername(String username);
	  boolean existsByUsername(String username);

	}

