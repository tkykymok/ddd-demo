package com.example.demo.domain.model.task;

public enum TaskStatus {
    UNCOMPLETED(0, "未完了"),
    IN_PROGRESS(1, "進行中"),
    COMPLETED(2, "完了");

    private final Integer code;
    private final String description;

    TaskStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
