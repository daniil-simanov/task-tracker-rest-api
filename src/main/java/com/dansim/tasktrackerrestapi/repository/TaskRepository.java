package com.dansim.tasktrackerrestapi.repository;

import com.dansim.tasktrackerrestapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    List<Task> findByOwnerId(int ownerId);

}
