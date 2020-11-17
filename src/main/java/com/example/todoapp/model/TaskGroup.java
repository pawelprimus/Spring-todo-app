package com.example.todoapp.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "task_groups")
public class TaskGroup {
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @NotBlank(message = "Task group's description must not be empty")
    private String description;
    private boolean done;
  /*  @AttributeOverrides(
            {
                    @AttributeOverride(column = @Column(name = "updatedOn"), name = "updatedOn")
            }) // adnotacja pomocnicza*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    // cascade czyli jak cos zmieniamy to zmieniamy we wszystkich jej taskach, jak usuwamy to wszystkie jej taski
    private Set<Task> tasks; // Lazy loading dla kolekcji, ustawione domyslnie,

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public TaskGroup() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

/*
    Audit getAudit() {
        return audit;
    }

    void setAudit(final Audit audit) {
        this.audit = audit;
    }*/

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(final Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(final Project project) {
        this.project = project;
    }
}
