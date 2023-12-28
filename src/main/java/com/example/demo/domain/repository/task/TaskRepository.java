package com.example.demo.domain.repository.task;

import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.model.valueobject.TaskId;


public interface TaskRepository {
    Task findById(TaskId id);

    void update(Task task);
}
