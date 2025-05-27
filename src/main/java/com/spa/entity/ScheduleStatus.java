package com.spa.entity;

public enum ScheduleStatus {
    PENDING("待确认"),
    CONFIRMED("已确认"),
    CANCELLED("已取消");

    private final String description;

    ScheduleStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 