package com.dansim.tasktrackerrestapi.controller;


import com.dansim.tasktrackerrestapi.dto.TaskDTO;
import com.dansim.tasktrackerrestapi.model.Task;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.service.TaskService;
import com.dansim.tasktrackerrestapi.service.UserService;
import com.dansim.tasktrackerrestapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final MapperUtil mapperUtil;
    private final Logger logger ;

    @GetMapping
    @Operation(summary = "Get all user tasks")
    public ResponseEntity<List<TaskDTO>> getTasks(@AuthenticationPrincipal User user){
        List<TaskDTO> tasks = taskService.findByOwnerId(user.getId()).stream()
                .map(mapperUtil::convertToTaskDTO)
                .toList();

        return ResponseEntity.ok()
                .body(tasks);

    }

    @PostMapping
    @Operation(summary = "Add task")
    public ResponseEntity<HttpStatus> addTask(@AuthenticationPrincipal User user,
                                              @RequestBody TaskDTO taskDTO ){
        Task task = mapperUtil.convertToTask(taskDTO);
        taskService.save(task,user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update task")
    public ResponseEntity<HttpStatus> updateTask(@AuthenticationPrincipal User user,
                                                 @PathVariable("id") int id,
                                                 @RequestBody TaskDTO taskDTO){
        Task task = mapperUtil.convertToTask(taskDTO);
        taskService.update(user,id,task);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task")
    public ResponseEntity<HttpStatus> deleteTask(@AuthenticationPrincipal User user,
                                                 @PathVariable("id") int id){
        taskService.delete(id);
        logger.info("Deleted task with id: " + id);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}

