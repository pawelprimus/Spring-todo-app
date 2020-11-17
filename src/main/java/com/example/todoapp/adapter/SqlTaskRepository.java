package com.example.todoapp.adapter;

/*
@RepositoryRestResource()
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(Task task);

    @RestResource(path = "done", rel = "done")
    List<Task> findByDone(@Param("state") boolean done);

}
*/

import com.example.todoapp.model.Task;
import com.example.todoapp.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlTaskRepository extends TaskRepository, JpaRepository<Task, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from tasks where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    boolean existsByDoneIsFalseAndGroup_Id(Integer groupId);

    @Override
    List<Task> findAllByGroup_Id(Integer groupId);
}
//@Override
//  @Query(nativeQuery = true, value = "select count(*) from tasks where id=:id")
//    boolean existsById(@Param("id") Integer id);

