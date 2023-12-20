package com.example.demo.application.usecase.task;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.repository.task.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskUsecase extends Usecase<UpdateTaskInput, Void> {

    private final TaskRepository taskRepository;

    public UpdateTaskUsecase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Void execute(UpdateTaskInput input) {
        // タスクの取得
        Task task = taskRepository.findById(input.getTaskId()).orElseThrow(() -> new RuntimeException("Task not found"));
        // タスクの更新
        task.addSubTask(input.getSubTaskTitle(), input.getSubTaskContent());
        // タスクの保存
        taskRepository.save(task);
        return null;
    }
}
