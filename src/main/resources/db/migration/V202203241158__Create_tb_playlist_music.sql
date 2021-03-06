CREATE TABLE `tb_playlist_music`
    (
        `playlist_id` VARCHAR(32) NOT NULL COMMENT '歌单ID',
        `music_id` VARCHAR(32) NOT NULL COMMENT '歌曲ID',
        CONSTRAINT c_pm_playlist_id
            FOREIGN KEY (playlist_id) REFERENCES `tb_playlist` (id),
        CONSTRAINT c_pm_music_id
            FOREIGN KEY (music_id) REFERENCES `tb_music` (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '歌单歌曲表';