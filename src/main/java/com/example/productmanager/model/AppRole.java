package com.example.productmanager.model;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
public class AppRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String roleName;
}
