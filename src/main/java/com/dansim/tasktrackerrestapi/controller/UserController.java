package com.dansim.tasktrackerrestapi.controller;

import com.dansim.tasktrackerrestapi.dto.UserDTO;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.util.MapperUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final MapperUtil mapperUtil;

    @GetMapping
    @Operation(summary = "Get current user")
    public UserDTO getUser(@AuthenticationPrincipal User user){
        return mapperUtil.convertToUserDTO(user);

    }
}
