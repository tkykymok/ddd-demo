package com.example.demo.application.usecase.task;


public record UpdateTaskInput(
        Long taskId,
        String subTaskTitle,
        String subTaskContent
) {
}
