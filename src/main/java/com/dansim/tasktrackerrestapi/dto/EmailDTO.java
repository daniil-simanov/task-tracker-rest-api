package com.dansim.tasktrackerrestapi.dto;

import lombok.*;



@Builder
@Data
public class EmailDTO  {
    private String recipientAddress;
    private String title;
    private String text;


}
