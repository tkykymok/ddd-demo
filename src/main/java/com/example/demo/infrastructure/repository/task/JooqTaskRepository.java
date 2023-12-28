package com.example.demo.infrastructure.repository.task;

import com.example.demo.domain.model.task.Task;
import com.example.demo.domain.model.task.TaskStatus;
import com.example.demo.domain.model.valueobject.TaskId;
import com.example.demo.domain.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demo.infrastructure.jooq.Tables.TASKS;

@Repository
@RequiredArgsConstructor
public class JooqTaskRepository implements TaskRepository {

    private final DSLContext dsl;

    @Override
    public Task findById(TaskId id) {
        List<Task> allTasks = findAllTasks();
        Map<TaskId, Task> hierarchicalTasks = buildTaskHierarchy(allTasks);
        return hierarchicalTasks.get(id);
    }

    @Override
    public void update(Task task) {
    }

    public List<Task> findAllTasks() {
        return dsl.selectFrom(TASKS)
                .fetch()
                .stream()
                .map(this::recordToTask)
                .collect(Collectors.toList());
    }

    public Map<TaskId, Task> buildTaskHierarchy(List<Task> tasks) {
        // タスクのIDをキーとしてタスクをマップに格納
        Map<TaskId, Task> taskMap = tasks.stream()
                .collect(Collectors.toMap(Task::getId, task -> task));

        // ルートタスクのMapを作成
        Map<TaskId, Task> rootTasksMap = new HashMap<>();

        // タスクをループして親子関係を構築
        for (Task task : tasks) {
            // 親タスクIDがnullの場合はルートタスクとしてリストに追加
            if (task.getParentId().value() == null) {
                rootTasksMap.put(task.getId(), task);
            } else { // 親タスクIDがnullでない場合は、親タスクを取得してサブタスクとして追加
                Task parent = taskMap.get(task.getParentId());
                if (parent != null) {
                    parent.getSubTasks().add(task);
                }
            }
        }

        return rootTasksMap;
    }


    private Task recordToTask(Record taskRecord) {
        TaskId id = new TaskId(taskRecord.get(TASKS.ID));
        String title = taskRecord.get(TASKS.TITLE);
        String content = taskRecord.get(TASKS.CONTENT);
        TaskStatus status = TaskStatus.valueOf(taskRecord.get(TASKS.STATUS));
        TaskId parentId = TaskId.of(taskRecord.get(TASKS.PARENT_ID));
        LocalDateTime createdAt = taskRecord.get(TASKS.CREATED_AT);
        return Task.reconstruct(id, title, content, status, parentId, createdAt);
    }

}
