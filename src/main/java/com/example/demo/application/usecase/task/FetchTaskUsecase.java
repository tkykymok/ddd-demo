package com.example.demo.application.usecase.task;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.repository.task.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FetchTaskUsecase extends Usecase<Long, Task> {

    private final TaskRepository taskRepository;

    public FetchTaskUsecase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Long taskId) {
        // タスクの取得
        Optional<Task> task = taskRepository.findById(taskId);
        // タスクが存在しない場合はnullを返す
        return task.orElse(null);
    }
}
