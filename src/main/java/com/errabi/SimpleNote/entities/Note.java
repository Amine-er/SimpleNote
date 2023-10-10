package com.errabi.SimpleNote.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "NOTE_LABEL_TABLE",
            joinColumns = {
            @JoinColumn(name = "note_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "label_id", referencedColumnName = "id")
    })
    private List<Label> labels;
    @OneToMany(mappedBy = "note", fetch = FetchType.LAZY)
    private List<Reminder> reminders;
    @Version
    protected Long version;

}
