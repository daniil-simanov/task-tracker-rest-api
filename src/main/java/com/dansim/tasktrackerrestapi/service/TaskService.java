package com.dansim.tasktrackerrestapi.service;

import com.dansim.tasktrackerrestapi.model.Task;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    public List<Task> findByOwnerId(int ownerId) {
        return taskRepository.findByOwnerId(ownerId);
    }

    @Transactional
    public void save(Task task, User user) {
        task.setOwner(user);
        task.setModified(LocalDateTime.now());
        taskRepository.save(task);
    }

    @Transactional
    public void update(User user,int id,Task updatedTask){
        updatedTask.setId(id);
        updatedTask.setOwner(user);
        updatedTask.setModified(LocalDateTime.now());
        taskRepository.save(updatedTask);

    }

    @Transactional
    public void delete(int id){
        taskRepository.deleteById(id);
    }


}
