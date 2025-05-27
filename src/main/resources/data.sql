-- 初始化用户数据
INSERT INTO users (username, password, name, phone, role, active, created_at, updated_at)
VALUES 
    ('zanboring', '123456', '张博仁', '13800138000', 'LOBBY_MANAGER', true, NOW(), NOW()),
    ('tyh', '123456', '唐雨涵', '13800138001', 'DEPARTMENT_MANAGER', true, NOW(), NOW()),
    ('lc', '123456', '李创', '13800138002', 'TECHNICIAN', true, NOW(), NOW()),
    ('jjking', '123456', '李喆', '13800138003', 'STAFF', true, NOW(), NOW())
ON DUPLICATE KEY UPDATE
    updated_at = NOW(); 