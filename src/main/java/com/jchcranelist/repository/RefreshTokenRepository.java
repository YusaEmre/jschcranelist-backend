package com.jchcranelist.repository;

import com.jchcranelist.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long>{

    RefreshToken findByUserId(Long userId);

    RefreshToken findByUser_Email(String userEmail);
}
