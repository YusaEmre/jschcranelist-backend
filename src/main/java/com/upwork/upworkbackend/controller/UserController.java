package com.upwork.upworkbackend.controller;

import com.upwork.upworkbackend.payload.request.UserRequest;
import com.upwork.upworkbackend.payload.response.JwtResponse;
import com.upwork.upworkbackend.security.TokenManager;
import com.upwork.upworkbackend.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserDetailServiceImpl userDetailService;
    private TokenManager tokenManager;
    private AuthenticationManager authenticationManager;

    public UserController(UserDetailServiceImpl userDetailService, TokenManager tokenManager, AuthenticationManager authenticationManager) {
        this.userDetailService = userDetailService;
        this.tokenManager = tokenManager;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserRequest userRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userRequest.getEmail(), userRequest.getPassword()
            ));
        }
        catch (BadCredentialsException e) {
            logger.debug(e.toString());
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            logger.debug(e.toString());
        }
        UserDetails userDetails = userDetailService.loadUserByUsername(userRequest.getEmail());
        String token = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
