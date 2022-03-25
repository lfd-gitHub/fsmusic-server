ALTER TABLE `tb_file`
    ADD COLUMN `creator_uid` VARCHAR(32) NOT NULL DEFAULT '1' COMMENT '创建者UID' AFTER `status`,
    ADD CONSTRAINT `c_file_creator_uid`
    FOREIGN KEY (`creator_uid`) REFERENCES `tb_user`(`id`);

ALTER TABLE `tb_file`
    ADD COLUMN `updater_uid` VARCHAR(32) NOT NULL DEFAULT '1' COMMENT '修改者UID' AFTER `creator_uid`,
    ADD CONSTRAINT `c_file_updater_uid`
    FOREIGN KEY (`updater_uid`) REFERENCES `tb_user`(`id`);
