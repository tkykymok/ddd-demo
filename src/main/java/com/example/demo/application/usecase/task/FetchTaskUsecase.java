package com.example.demo.application.usecase.task;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.model.valueobject.TaskId;
import com.example.demo.domain.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchTaskUsecase extends Usecase<Long, Task> {

    private final TaskRepository taskRepository;

    @Override
    public Task execute(Long taskId) {
        // タスクの取得
        Task task = taskRepository.findById(new TaskId(taskId));

        if (task == null) {
            throw new RuntimeException("Task not found");
        }
        // タスクが存在しない場合はnullを返す
        return task;
    }
}
