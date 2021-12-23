## S004 定义用户、角色表及相关业务
### Steps
- [Draw.io](https://app.diagrams.net/) [绘制表结构](src/test/resources/tbdraws/S004_1_t_user_row.drawio) `tb_user|tb_user_role|tb_role`
- [Flyway](https://flywaydb.org/documentation/getstarted/firststeps/maven)创建和更新表
- Flyway - 创建db/migration/sql
- [Ksuid](https://github.com/ksuid/ksuid) 引入
- [MapStruct](https://mapstruct.org/documentation/installation/) 复制JavaBean
--------------------------------------------------------
## S003 安装[MYSQL](https://www.jianshu.com/p/eb3d9129d880)
### Steps
- `docker pull mysql/mysql-server:latest`
--------------------------------------------------------
## S002 配置[VS](https://blog.csdn.net/zyd573803837/article/details/109263219)
### Steps  
- Java Extension
- Spring Boot Extension
- Java > Configuration > Maven: UserSetting
----------------------------------------------------------
## S001 配置[Maven](https://maven.apache.org/download.cgi)
### Steps
- 配置M2_HOME、PATH
- 配置本地仓库路径 `~/.m2/setting.xml | <localRepository>`
- [配置镜像](https://developer.aliyun.com/mirror/maven)
------------------------------------------------------------
## ☆ REFS:
- [音乐APP全栈开发](https://www.bilibili.com/video/BV1XQ4y1a7mX?spm_id_from=333.999.0.0) |  https://www.bilibili.com/video/BV1XQ4y1a7mX?spm_id_from=333.999.0.0