package com.example.productmanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productmanager.model.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	AppRole findByRoleName(String roleName);
	}

