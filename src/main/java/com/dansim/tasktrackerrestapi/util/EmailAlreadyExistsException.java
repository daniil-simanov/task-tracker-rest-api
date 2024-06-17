package com.dansim.tasktrackerrestapi.util;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String msg){
        super(msg);
    }
}
