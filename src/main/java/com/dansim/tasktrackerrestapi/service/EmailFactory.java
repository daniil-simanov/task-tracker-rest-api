package com.dansim.tasktrackerrestapi.service;

import com.dansim.tasktrackerrestapi.dto.EmailDTO;
import com.dansim.tasktrackerrestapi.model.User;
import org.springframework.stereotype.Service;

@Service
public class EmailFactory {

    public EmailDTO createEmail(User user){
        return EmailDTO
                .builder()
                .recipientAddress(user.getEmail())
                .title("Successful registration")
                .text("Welcome to our task tracking service!")
                .build();
    }
}
