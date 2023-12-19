package com.example.demo.domain.model.task;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "SUB_TASKS")
public class SubTask {
    @Id
    private Long id;
    private Long parentId;
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime createdAt;

    // プライベートコンストラクタで直接のインスタンス化を防ぐ
    private SubTask() {}


    // ファクトリーメソッド
    public static SubTask create(Long parentId, String title, String content) {
        SubTask subTask = new SubTask();
        subTask.parentId = parentId;
        subTask.title = title;
        subTask.content = content;
        subTask.status = TaskStatus.UNCOMPLETED;
        subTask.createdAt = LocalDateTime.now(); // 現在の日時で初期化
        return subTask;
    }



    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
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

}
