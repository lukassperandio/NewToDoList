package com.sperandio.lukas.NewToDoList.repository;

import com.sperandio.lukas.NewToDoList.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
