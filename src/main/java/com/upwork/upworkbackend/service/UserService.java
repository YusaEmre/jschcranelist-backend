package com.upwork.upworkbackend.service;

import com.upwork.upworkbackend.model.User;
import com.upwork.upworkbackend.payload.request.UserRequest;
import com.upwork.upworkbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public void saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new IllegalStateException("This user email already taken");
        }else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }
}
