package com.jchcranelist.repository;

import com.jchcranelist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User getUserByEmail(String email);

    boolean existsByEmail(String email);
}
