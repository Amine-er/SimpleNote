package com.errabi.SimpleNote.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Label> labels;
    @Version
    protected Long version;

}
