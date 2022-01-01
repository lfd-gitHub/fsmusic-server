## S005 [Swagger3](https://springfox.github.io/springfox/docs/current/) & [JWT](https://javatodev.com/spring-boot-jwt-authentication/)
- [集成Swagger3](https://cloud.tencent.com/developer/article/1729796) 
- [S3版本问题](https://stackoverflow.com/questions/70178343/springfox-3-0-0-is-not-working-with-spring-boot-2-6-0)
  - BeanPostProcessor
  - `spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER`
- [s3集成JWT](https://mp.weixin.qq.com/s?__biz=MzUzMzQ2MDIyMA%3D%3D&chksm=faa2f47ecdd57d68f2e545a496c38a2d43c8851f491a1a98ffbba1d5bb437cad98cd976d2243&idx=1&mid=2247487981&scene=21&sn=382fe4312de5c7aae8d3341bac08e104#wechat_redirect)

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
- `docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mysql/mysql-server`
- `docker exec -it mysql bash`
- `CREATE USER 'root'@'%' IDENTIFIED BY 'root';`
- `GRANT ALL ON *.* TO 'root'@'%';`
- `flush privileges;`
- `ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY '123456';`
- `flush privileges;`
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