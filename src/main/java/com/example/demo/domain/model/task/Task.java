package com.example.demo.domain.model.task;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "tasks")
public class Task {
    @Id
    @Column("id")
    private Long id;
    @Column("title")
    private String title;
    @Column("content")
    private String content;
    @Column("status")
    private TaskStatus status;
    @Column("created_at")
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "parent_id", keyColumn = "id")
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
