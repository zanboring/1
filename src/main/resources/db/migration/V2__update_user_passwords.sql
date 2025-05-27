-- 更新用户密码为BCrypt加密的123456
UPDATE users 
SET password = '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi' 
WHERE username IN ('tyh', 'lc', 'jjking'); 