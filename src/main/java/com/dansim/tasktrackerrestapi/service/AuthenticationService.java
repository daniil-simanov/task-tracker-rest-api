package com.dansim.tasktrackerrestapi.service;

import com.dansim.tasktrackerrestapi.dto.EmailDTO;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailFactory emailFactory;
    private final KafkaTemplate<String, EmailDTO> kafkaTemplate;
    private final Logger logger;

    @Transactional
    public void register(User user) {
        logger.info("Registering user: " + user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        kafkaTemplate.send("emailTopic",emailFactory.createEmail(user));
        logger.info("Sending email to user: " + user.getUsername());
        userRepository.save(user);
        logger.info("Saved user: " + user.getUsername());
    }
}
