package com.sperandio.lukas.NewToDoList.service;

import com.sperandio.lukas.NewToDoList.dto.request.TaskRequest;
import com.sperandio.lukas.NewToDoList.dto.response.TaskResponse;
import com.sperandio.lukas.NewToDoList.model.Task;
import com.sperandio.lukas.NewToDoList.model.User;
import com.sperandio.lukas.NewToDoList.repository.TaskRepository;
import com.sperandio.lukas.NewToDoList.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskResponse> getAll(){
        return taskRepository.findAll()
                .stream()
                .map(u -> new TaskResponse(u.getId(), u.getTitle(), u.getDescription(),  u.getPriority(), u.getCompleted(), u.getUser().getId()))
                .toList();
    }

    public TaskResponse addTask(TaskRequest taskRequest){
        Task task = new Task();

        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setPriority(taskRequest.priority());
        task.setCompleted(taskRequest.completed());
        User user = userRepository.findById(taskRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        Task saved = taskRepository.save(task);
        return new TaskResponse(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getPriority(),saved.getCompleted(), saved.getUser().getId());
    }

    public TaskResponse getById(long id){
        return taskRepository.findById(id)
                .map(t -> new TaskResponse(t.getId(), t.getTitle(), t.getDescription(), t.getPriority(), t.getCompleted(), t.getUser().getId()))
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public TaskResponse updateTask(long id, TaskRequest taskRequest){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        task.setTitle(taskRequest.title());
        task.setDescription(taskRequest.description());
        task.setPriority(taskRequest.priority());
        task.setCompleted(taskRequest.completed());
        User user = userRepository.findById(taskRequest.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        TaskResponse taskResponse = new TaskResponse(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.getCompleted(),
                task.getUser().getId());
        taskRepository.save(task);
        return taskResponse;
    }

    public void delete(long id){
        taskRepository.deleteById(id);
    }
}
