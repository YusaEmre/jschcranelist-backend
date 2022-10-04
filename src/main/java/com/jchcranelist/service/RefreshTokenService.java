package com.jchcranelist.service;

import com.jchcranelist.model.RefreshToken;
import com.jchcranelist.model.User;
import com.jchcranelist.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.reftesh.token.expiration.time.month}")
    long expireMonth;

    private final RefreshTokenRepository refreshTokenRepository;


    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }


    public String createRefreshToken(User user){
        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(LocalDateTime.now().minusMonths(expireMonth));
        refreshTokenRepository.save(token);
        return token.getToken();
    }


    public boolean isRefreshExpired(RefreshToken token){
         return  token.getExpiryDate().isBefore(LocalDateTime.now());
    }

    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    public RefreshToken getByUserName(String userName) {
        return refreshTokenRepository.findByUser_Email(userName);
    }
}
