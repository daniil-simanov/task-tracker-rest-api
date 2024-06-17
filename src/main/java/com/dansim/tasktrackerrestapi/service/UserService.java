package com.dansim.tasktrackerrestapi.service;

import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

}
