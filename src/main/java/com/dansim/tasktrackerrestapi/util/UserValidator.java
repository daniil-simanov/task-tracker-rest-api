package com.dansim.tasktrackerrestapi.util;

import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class UserValidator implements Validator {

    private final UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (userService.findByEmail(user.getEmail()).isPresent()){
            errors.rejectValue("email","This email is already taken");
        }


    }
}
