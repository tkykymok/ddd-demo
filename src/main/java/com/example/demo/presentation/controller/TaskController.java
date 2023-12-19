package com.example.demo.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.application.usecase.TaskUsecase;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskUsecase taskUsecase;

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getTask(@PathVariable Long taskId) {

        return ResponseEntity.ok(taskUsecase.fetchTask(taskId));
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId) {

        taskUsecase.updateTask(taskId);

        return ResponseEntity.ok(null);
    }

}
