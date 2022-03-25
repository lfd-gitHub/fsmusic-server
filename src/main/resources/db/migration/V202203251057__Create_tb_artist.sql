CREATE TABLE tb_artist
    (
        `id` VARCHAR(32) NOT NULL
            PRIMARY KEY COMMENT '歌手ID',
        `name` VARCHAR(64) NOT NULL COMMENT '歌手名字',
        `remark` TEXT NULL COMMENT '歌手备注',
        `cover_id` VARCHAR(32) NULL COMMENT '歌手封面ID',
        `status` VARCHAR(32) DEFAULT 'DRAFT' NOT NULL COMMENT '歌单上架状态，DRAFT-草稿，PUBLISHED-已上架，BLOCKED-已下架',
        `creator_uid` VARCHAR(32) NOT NULL COMMENT '创建人ID',
        `updater_uid` VARCHAR(32) NOT NULL COMMENT '修改人ID',
        `create_time` datetime(6) NOT NULL COMMENT '创建时间',
        `update_time` datetime(6) NOT NULL COMMENT '更新时间',
        CONSTRAINT c_artist_cover_id
            FOREIGN KEY (cover_id) REFERENCES `tb_file` (id),
        CONSTRAINT c_artist_creator_uid
            FOREIGN KEY (creator_uid) REFERENCES `tb_user` (id),
        CONSTRAINT c_artist_updater_uid
                    FOREIGN KEY (updater_uid) REFERENCES `tb_user` (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '歌手表';