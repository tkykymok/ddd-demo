package com.example.demo.application.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.repository.TaskRepository;

@Service
public class TaskUsecase {

    @Autowired
    private TaskRepository taskRepository;

    public Task fetchTask(Long taskId) {

        Optional<Task> task = taskRepository.findById(taskId);

        return task.orElse(null);
    }

    public void updateTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.addSubTask("サブタスクのタイトル", "サブタスクの内容");
        taskRepository.save(task);
    }

}
