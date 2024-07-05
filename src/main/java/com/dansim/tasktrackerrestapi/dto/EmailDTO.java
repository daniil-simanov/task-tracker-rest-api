package com.dansim.tasktrackerrestapi.dto;

import lombok.*;



@Builder
@Setter
@Getter
public class EmailDTO  {
    private String recipientAddress;
    private String title;
    private String text;


}
