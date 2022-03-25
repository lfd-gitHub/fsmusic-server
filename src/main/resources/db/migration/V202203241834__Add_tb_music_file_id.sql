ALTER TABLE `tb_music`
    ADD COLUMN `file_id` VARCHAR(32) NULL COMMENT '音乐文件ID' AFTER `description`,
    ADD CONSTRAINT `c_music_file_id`
    FOREIGN KEY (`file_id`) REFERENCES `tb_file`(`id`);
