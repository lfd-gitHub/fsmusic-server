CREATE TABLE tb_user(
    id VARCHAR(32) NOT NULL PRIMARY KEY COMMENT '用户ID',
    username VARCHAR(64) NOT NULL COMMENT '用户名',
    nickname VARCHAR(64) NULL COMMENT '昵称',
    password VARCHAR(64) NOT NULL COMMENT '密码',
    gender VARCHAR(10) NULL COMMENT '性别',
    locked TINYINT(1) DEFAULT 0 NOT NULL COMMENT '锁定状态',
    enabled TINYINT(1) DEFAULT 0 NOT NULL COMMENT '可用状态',
    last_login_ip VARCHAR(64) NULL COMMENT '登陆IP',
    last_login_time DATETIME(6) NULL COMMENT '登陆时间',
    create_time DATETIME(6) NOT NULL COMMENT '创建时间',
    update_time DATETIME(6) NOT NULL COMMENT '更新时间',
    CONSTRAINT uk_user_username UNIQUE (username)
) ENGINE=InnoDb DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '用户表'