package com.dansim.tasktrackerrestapi.controller;

import com.dansim.tasktrackerrestapi.service.JwtService;
import com.dansim.tasktrackerrestapi.dto.UserRegisterDTO;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.service.AuthenticationService;
import com.dansim.tasktrackerrestapi.util.EmailAlreadyExistsException;
import com.dansim.tasktrackerrestapi.util.MapperUtil;
import com.dansim.tasktrackerrestapi.util.ErrorResponse;
import com.dansim.tasktrackerrestapi.util.UserValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

import static com.dansim.tasktrackerrestapi.util.ErrorsUtil.returnErrorsToClient;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MapperUtil mapperUtil;
    private final UserValidator userValidator;
    private final Logger logger;

    @PostMapping("/registration")
    @Operation(summary = "User registration")
    public ResponseEntity<?> registration(@RequestBody @Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult){
        logger.info("User registration");
        User user = mapperUtil.convertToUser(userRegisterDTO);
        logger.info(userRegisterDTO.toString());
        userValidator.validate(user,bindingResult);
        logger.info("User validate");
        if (bindingResult.hasErrors()){
            returnErrorsToClient(bindingResult);
        }
        authenticationService.register(user);
        logger.info("User registered");
        String jwtToken = jwtService.generateToken(user);
        logger.info("JWT token generated");
        return new ResponseEntity<>(jwtToken,HttpStatus.OK);


    }

    @PostMapping("/authentication")
    @Operation(summary = "User authentication")
    public ResponseEntity<?> authentication(@RequestBody @Valid UserRegisterDTO userRegisterDTO, BindingResult bindingResult){
        User user = mapperUtil.convertToUser(userRegisterDTO);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()
        )
        );
        String jwtToken = jwtService.generateToken(user);
        return new ResponseEntity<>(jwtToken,HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(EmailAlreadyExistsException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
