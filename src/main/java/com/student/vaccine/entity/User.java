package com.student.vaccine.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users" , schema = "school_vaccination_portal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;  // example: "ADMIN"
}
