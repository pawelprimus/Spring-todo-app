package com.example.todoapp.logic;

import com.example.todoapp.model.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    public static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    public final TaskRepository repository;

    TaskService(final TaskRepository repository) {
        this.repository = repository;
    }

  /*  @Async
    public CompletableFuture<List<Task>> findAllAsync() {
        logger.info("Supply async!");
        return CompletableFuture.supplyAsync(repository::findAll); // w sposob asynchroniczny na osobnym watku zapytujemy
    }*/

}
