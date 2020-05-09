package com.marcosjr.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
