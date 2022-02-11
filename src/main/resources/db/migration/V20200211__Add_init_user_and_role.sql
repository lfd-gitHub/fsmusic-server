INSERT INTO `tb_user` (id, username, nickname, password, enabled,create_time, update_time)
VALUES ('1', 'admin', 'admin', '$2a$10$UI34EiEE22qZh.zPFUtUgun3dd2ttpwv43gAnmsht5BXCrRLI9Zi2',1,
        '2021-07-21 09:27:12.260000',
        '2021-07-21 09:27:12.260000');
INSERT INTO `tb_user` (id, username, nickname, password, enabled,create_time, update_time)
VALUES ('2', 'test', 'test', '$2a$10$UI34EiEE22qZh.zPFUtUgun3dd2ttpwv43gAnmsht5BXCrRLI9Zi2',1,
        '2021-07-21 09:27:12.260000',
        '2021-07-21 09:27:12.260000');
INSERT INTO `tb_role` (id, name, title, create_time, update_time)
VALUES ('1', 'ROLE_USER', '普通用户', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `tb_role` (id, name, title, create_time, update_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', '2021-07-21 09:27:12.260000', '2021-07-21 09:27:12.260000');
INSERT INTO `tb_user_role` (user_id, role_id)
VALUES ('1', '1');
INSERT INTO `tb_user_role` (user_id, role_id)
VALUES ('1', '2');
INSERT INTO `tb_user_role` (user_id, role_id)
VALUES ('2', '1');