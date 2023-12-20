package com.example.demo.application.usecase.task;


public class UpdateTaskInput {

    private Long taskId;
    private String subTaskTitle;
    private String subTaskContent;

    // コンストラクタ、ゲッター、セッターなど

    public UpdateTaskInput(Long taskId, String subTaskTitle, String subTaskContent) {
        this.taskId = taskId;
        this.subTaskTitle = subTaskTitle;
        this.subTaskContent = subTaskContent;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getSubTaskTitle() {
        return subTaskTitle;
    }

    public String getSubTaskContent() {
        return subTaskContent;
    }

    // 必要に応じてバリデーションロジックやヘルパーメソッドを追加
}
