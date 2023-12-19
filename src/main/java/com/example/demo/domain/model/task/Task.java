package com.example.demo.domain.model.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "TASKS")
public class Task {
    @Id
    private Long id;
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "PARENT_ID", keyColumn = "ID")
    private List<SubTask> subTasks; // 子タスクのリスト




    public void addSubTask(String title, String content) {
        subTasks.add(SubTask.create(id, title, content));
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }







}
