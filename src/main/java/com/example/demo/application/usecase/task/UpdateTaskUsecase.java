package com.example.demo.application.usecase.task;

import com.example.demo.application.usecase.Usecase;
import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.model.valueobject.TaskId;
import com.example.demo.domain.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTaskUsecase extends Usecase<UpdateTaskInput, Void> {

    private final TaskRepository taskRepository;


    @Override
    @Transactional
    public Void execute(UpdateTaskInput input) {
        // タスクの取得
        Task task = taskRepository.findById(TaskId.of(input.taskId()));
        if (task == null) {
            throw new RuntimeException("Task not found");
        }

        // タスクの更新
        task.addSubTask(input.subTaskTitle(), input.subTaskContent());
        // タスクの保存
        taskRepository.update(task);
        return null;
    }
}
