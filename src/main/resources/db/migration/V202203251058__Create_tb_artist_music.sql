CREATE TABLE tb_artist_music
    (
        `artist_id` VARCHAR(32) NOT NULL COMMENT '歌手ID',
        `music_id` VARCHAR(32) NOT NULL COMMENT '歌曲ID',
        CONSTRAINT c_am_artist_id
            FOREIGN KEY (artist_id) REFERENCES `tb_artist` (id),
        CONSTRAINT c_am_music_id
            FOREIGN KEY (music_id) REFERENCES `tb_music` (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '歌单歌曲表';