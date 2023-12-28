package com.example.demo.domain.model.task;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.TaskId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Task extends SingleKeyBaseEntity<TaskId> {
    private String title;
    private String content;
    private TaskStatus status;
    private TaskId parentId;
    private LocalDateTime createdAt;
    private List<Task> subTasks;

    // プライベートコンストラクタで直接のインスタンス化を防ぐ
    private Task() {
    }

    // ファクトリメソッド
    public static Task reconstruct(TaskId taskId, String title, String content,
                                   TaskStatus status, TaskId parentId, LocalDateTime createdAt) {
        Task task = new Task();
        task.id = taskId;
        task.title = title;
        task.content = content;
        task.status = status;
        task.parentId = parentId;
        task.createdAt = createdAt;
        task.subTasks = new ArrayList<>();
        return task;
    }

//    public void addSubTask(String title, String content) {
//        subTasks.add(SubTask.create(this.getId(), title, content));
//    }
}
