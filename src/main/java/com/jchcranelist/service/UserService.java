package com.jchcranelist.service;

import com.jchcranelist.exception.NotFoundException;
import com.jchcranelist.model.User;
import com.jchcranelist.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Value("${admin.email}")
    private String adminEmail;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void saveUser(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            if(!user.getEmail().equals(adminEmail)){
                throw new IllegalStateException("This user email already taken");
            }
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        }
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User not found"));
    }

    public User getUserByUserName(String userName){
        return userRepository.getUserByEmail(userName);
    }
}
