CREATE TABLE tb_playlist
    (
        `id` VARCHAR(32) NOT NULL
            PRIMARY KEY COMMENT '歌单ID',
        `name` VARCHAR(64) NOT NULL COMMENT '歌单名字',
        `description` TEXT NULL COMMENT '歌单简介',
        `cover_id` VARCHAR(32) NULL COMMENT '歌单封面ID',
        `status` VARCHAR(32) DEFAULT 'DRAFT' NOT NULL COMMENT '歌单上架状态，DRAFT-草稿，PUBLISHED-已上架，CLOSED-已下架',
        `creator_id` VARCHAR(32) NOT NULL COMMENT '创建人ID',
        `create_time` datetime(6) NOT NULL COMMENT '创建时间',
        `update_time` datetime(6) NOT NULL COMMENT '更新时间',
        CONSTRAINT c_cover_id
            FOREIGN KEY (cover_id) REFERENCES `tb_file` (id),
        CONSTRAINT c_creator_id
            FOREIGN KEY (creator_id) REFERENCES `tb_user` (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '歌单表';