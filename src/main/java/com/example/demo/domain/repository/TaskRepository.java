package com.example.demo.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.model.task.Task;


public interface TaskRepository extends CrudRepository<Task, Long> {
    // タスクの取得 (TaskIdによる)
    @Override
    Optional<Task> findById(Long id);

    // タスクの保存 (新規作成と更新)
    @Override
    <S extends Task> S save(S entity);

    // タスクの削除 (TaskIdによる)
    @Override
    void deleteById(Long id);
}
