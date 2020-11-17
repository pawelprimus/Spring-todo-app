package com.example.todoapp.model;


import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

//@MappedSuperclass
@Embeddable
class Audit {
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;


    @PrePersist
        //przed zapisem sie wbija, sluzy do insertow
    void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preMerge() {
        updatedOn = LocalDateTime.now();
    }
}
