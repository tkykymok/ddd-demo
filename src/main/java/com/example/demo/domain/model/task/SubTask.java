package com.example.demo.domain.model.task;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.SubTaskId;
import com.example.demo.domain.model.valueobject.TaskId;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "SUB_TASKS")
public class SubTask extends SingleKeyBaseEntity<SubTaskId> {
    private TaskId parentId;
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime createdAt;

    // プライベートコンストラクタで直接のインスタンス化を防ぐ
    private SubTask() {}


    // ファクトリーメソッド
    public static SubTask create(TaskId parentId, String title, String content) {
        SubTask subTask = new SubTask();
        subTask.parentId = parentId;
        subTask.title = title;
        subTask.content = content;
        subTask.status = TaskStatus.UNCOMPLETED;
        subTask.createdAt = LocalDateTime.now(); // 現在の日時で初期化
        return subTask;
    }

    public TaskId getParentId() {
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
