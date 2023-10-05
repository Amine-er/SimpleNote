package com.errabi.SimpleNote.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data @NoArgsConstructor
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long labelId;
    private String name;
    private String color;
    @ManyToMany
    private List<Note> notes;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

}
