package com.sperandio.lukas.NewToDoList.repository;

import com.sperandio.lukas.NewToDoList.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
