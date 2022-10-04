package com.jchcranelist.controller;

import com.jchcranelist.model.RefreshToken;
import com.jchcranelist.model.User;
import com.jchcranelist.payload.request.RefreshRequest;
import com.jchcranelist.payload.response.AuthResponse;
import com.jchcranelist.payload.request.UserRequest;
import com.jchcranelist.security.TokenManager;
import com.jchcranelist.service.RefreshTokenService;
import com.jchcranelist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final TokenManager tokenManager;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> userLogin(@RequestBody UserRequest userRequest) throws Exception {
        logger.info(userRequest.toString());
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

        User user  = userService.getUserByUserName(userRequest.getEmail());
        String token = tokenManager.generateJwtTokenByUserName(user.getEmail());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setUserName(user.getEmail());
        authResponse.setMessage("Login success");
        authResponse.setAccessToken(token);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        return ResponseEntity.ok(authResponse);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/register")
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }



    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest){
        AuthResponse authResponse = new AuthResponse();
        RefreshToken refreshToken =  refreshTokenService.getByUserName(refreshRequest.getUserName());
        if(refreshToken.getToken().equals(refreshRequest.getRefreshToken())&& !refreshTokenService.isRefreshExpired(refreshToken)){

            User user  = refreshToken.getUser();
            String token = tokenManager.generateJwtToken(user);

            authResponse.setUserName(user.getEmail());
            authResponse.setMessage("Token refreshed");
            authResponse.setAccessToken(token);
            authResponse.setRefreshToken(refreshToken.getToken());

            return new ResponseEntity<>(authResponse,HttpStatus.OK);

        }else{
            authResponse.setMessage("Refresh token is not valid");
            return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
        }
    }



}


