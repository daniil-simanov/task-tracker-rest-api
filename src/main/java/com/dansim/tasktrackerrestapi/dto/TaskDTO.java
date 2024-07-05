package com.dansim.tasktrackerrestapi.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String header;
    private String description;
}
