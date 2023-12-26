package com.example.demo.domain.model.task;

import com.example.demo.domain.model.SingleKeyBaseEntity;
import com.example.demo.domain.model.valueobject.TaskId;
import lombok.Getter;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Table(name = "tasks")
public class Task extends SingleKeyBaseEntity<TaskId> {
    private String title;
    private String content;
    private TaskStatus status;
    private LocalDateTime createdAt;

    @MappedCollection(idColumn = "parent_id")
    private Set<SubTask> subTasks; // 子タスクのリスト

    // プライベートコンストラクタで直接のインスタンス化を防ぐ
    private Task() {
    }

    public void addSubTask(String title, String content) {
        subTasks.add(SubTask.create(this.getId(), title, content));
    }
}
