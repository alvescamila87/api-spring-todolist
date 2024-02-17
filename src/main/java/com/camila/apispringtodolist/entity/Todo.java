package com.camila.apispringtodolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "todos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    private boolean done;
    private int priority;

    public Todo(String name, String description, boolean done, int priority) {
        this.name = name;
        this.description = description;
        this.done = done;
        this.priority = priority;
    }
}
