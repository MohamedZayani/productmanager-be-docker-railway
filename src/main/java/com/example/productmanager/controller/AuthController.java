package com.example.productmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.productmanager.dto.AuthRequest;
import com.example.productmanager.dto.AuthResponse;
import com.example.productmanager.service.JwtService;

@RestController
@RequestMapping("/api/auth")
//@CrossOrigin(origins = "http://localhost:4200") // 👈 Autorise Angular
public class AuthController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtService jwtService;

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = jwtService.generateToken(authentication);

    return ResponseEntity.ok(new AuthResponse(token));
  }
}
