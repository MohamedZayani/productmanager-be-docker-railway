package com.example.productmanager.service;

import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.productmanager.model.AppUser;
import com.example.productmanager.repository.AppUserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

  private final AppUserRepository userRepository;

  public MyUserDetailsService(AppUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AppUser appUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    Collection<SimpleGrantedAuthority> authorities = appUser.getRoles()
      .stream()
      .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
      .toList();

    return new org.springframework.security.core.userdetails.User(
      appUser.getUsername(),
      appUser.getPassword(),
      authorities
    );
  }
}
