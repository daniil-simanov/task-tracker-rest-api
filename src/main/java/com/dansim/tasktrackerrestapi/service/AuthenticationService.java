package com.dansim.tasktrackerrestapi.service;

import com.dansim.tasktrackerrestapi.dto.EmailDTO;
import com.dansim.tasktrackerrestapi.model.User;
import com.dansim.tasktrackerrestapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailFactory emailFactory;
    private final KafkaTemplate<String, EmailDTO> kafkaTemplate;

    @Transactional
    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        kafkaTemplate.send("emailTopic",emailFactory.createEmail(user));
        userRepository.save(user);
    }
}
