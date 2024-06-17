package com.dansim.tasktrackerrestapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "header")
    private String header;

    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @Column(name = "modified")
    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;
}
