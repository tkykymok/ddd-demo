package com.example.demo.domain.model.task;

import com.example.demo.domain.model.BaseEntity;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "TASKS")
public class Task extends BaseEntity {
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "PARENT_ID", keyColumn = "ID")
    private List<SubTask> subTasks; // 子タスクのリスト

    // プライベートコンストラクタで直接のインスタンス化を防ぐ
    private Task() {}

    public void addSubTask(String title, String content) {
        subTasks.add(SubTask.create(this.getId(), title, content));
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
