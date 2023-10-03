package com.errabi.SimpleNote.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Note> notes ;
    @Version
    protected Long version;

}
