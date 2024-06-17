package com.dansim.tasktrackerrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDTO {
    private String header;
    private String description;
}
