package com.spa.entity;

public enum LeaveStatus {
    PENDING("待审批"),
    APPROVED("已批准"),
    REJECTED("已拒绝");

    private final String description;

    LeaveStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
} 