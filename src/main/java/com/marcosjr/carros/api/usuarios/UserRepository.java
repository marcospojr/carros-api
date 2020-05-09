package com.marcosjr.carros.api.usuarios;

import com.marcosjr.carros.api.usuarios.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
