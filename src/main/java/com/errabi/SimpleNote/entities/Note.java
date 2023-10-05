package com.errabi.SimpleNote.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title ;
    private String content ;
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate dateCreated ;
    @UpdateTimestamp
    private LocalDate lastUpdated ;
    private Boolean isArchived ;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user ;
    @OneToMany(mappedBy = "note", fetch = FetchType.LAZY)
    private List<Reminder> reminders;
    @Version
    protected Long version;
}
