package com.dansim.tasktrackerrestapi.controller;

import com.dansim.tasktrackerrestapi.dto.TaskDTO;
import com.dansim.tasktrackerrestapi.model.Task;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.service.TaskService;
import com.dansim.tasktrackerrestapi.util.MapperUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @Mock
    private MapperUtil mapperUtil;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTasks_ReturnsListOfTaskDTO() {
        //given
        User user = new User(1,"user@gmail.com","password", List.of());
        List<Task> testTasks =  Stream.of(
                new TaskDTO("header1","description1"),
                new TaskDTO("header2","description2")
        )
                .map(mapperUtil::convertToTask)
                .toList();;
        Mockito.doReturn(testTasks).when(taskService).findByOwnerId(user.getId());

        //when
        var responseEntity = taskController.getTasks(user);

        //then
        assertNotNull(responseEntity);
        assertEquals(
                testTasks.stream().map(mapperUtil::convertToTaskDTO).toList(),
                responseEntity.getBody());
    }

    @Test
    void addTask_ReturnsValidResponseEntity(){
        //given
        TaskDTO taskDTO = new TaskDTO("header","description");
        User user = new User(1,"user@gmail.com","password", List.of());

        //when
        var responseEntity = taskController.addTask(user,taskDTO);

        //then
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}