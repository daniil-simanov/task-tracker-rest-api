package com.dansim.tasktrackerrestapi.util;

import com.dansim.tasktrackerrestapi.dto.TaskDTO;
import com.dansim.tasktrackerrestapi.dto.UserDTO;
import com.dansim.tasktrackerrestapi.dto.UserRegisterDTO;
import com.dansim.tasktrackerrestapi.model.Task;
import com.dansim.tasktrackerrestapi.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    private final ModelMapper modelMapper;

    public User convertToUser(UserRegisterDTO userRegisterDTO){return modelMapper.map(userRegisterDTO, User.class);}

    public UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    public Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
