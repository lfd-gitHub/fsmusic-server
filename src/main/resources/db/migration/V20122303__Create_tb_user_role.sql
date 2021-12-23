CREATE TABLE tb_user_role(
    user_id VARCHAR(128) NOT NULL COMMENT '用户ID',
    role_id VARCHAR(128) NULL COMMENT '角色ID',
    CONSTRAINT c_user_id FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT c_role_id FOREIGN KEY (role_id) REFERENCES tb_role(id)
) ENGINE=InnoDb DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户角色关联表'