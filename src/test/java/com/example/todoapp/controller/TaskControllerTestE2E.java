package com.example.todoapp.controller;

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@Primary
//@ActiveProfiles("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//wlasciwosc jak my chcemy wsadzac context springow
class TaskControllerTestE2E {
    //wstrzykiwanie przez pola nie jest najlepsze
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;//prywatna klasa ktora pozwaala odpytywac inne uslugi, strzelanie tak jak postmanem

    @Autowired
    TaskRepository repo;

    @Test
    void httpGet_returnsAllTasks() {
        //given
        int initial = repo.findAll().size();
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));
        //when
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        //then
        assertThat(result).hasSize(initial + 2);
    }

    @Test
    void httpDelete_deleteWithGivenId() {
        //given
        int initial = repo.findAll().size();
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));
        repo.save(new Task("three", LocalDateTime.now()));
        //when
        repo.deleteById(2);
        Task[] result = restTemplate.getForObject("http://localhost:" + port + "/tasks", Task[].class);

        //then
        assertThat(result).hasSize(initial + 2);
    }


    @Test
    void httpPut_update() {
        //given
        final String uri = "http://localhost:" + port + "/tasks/" + 2;
        repo.save(new Task("foo", LocalDateTime.now()));
        repo.save(new Task("bar", LocalDateTime.now()));
        repo.save(new Task("three", LocalDateTime.now()));

        Task taskToUpdate = new Task("updated", LocalDateTime.now());

        //when
        restTemplate.put(uri, taskToUpdate);
        Task taskAfterUpdate = restTemplate.getForObject(uri, Task.class);
        //then
        assertThat(taskAfterUpdate.getDescription()).isEqualTo(taskToUpdate.getDescription());
    }


}