# 工程简介
美团leaf项目地址(https://github.com/Meituan-Dianping/Leaf  )  
二开美团leaf分布式ID  
主要改造注册配置中心到nacos，数据库适配mysql8.0，屏蔽zk接口，依赖版本升级，整体项目结构重建，代码大部分复用开源代码。

## 项目结构
### annotation包
leaf-boot-starter注解包，主要用于加载leaf配置和初始化DataSource
### controller
对外提供接口
### core
核心包，包括id生成器，定时队列拉取，雪花id生成器等核心代码
### model
页面view
### nacosconfig/nacosdiscovery
nacos注册发现启动配置类

### 监控接口
http://localhost:9949/cache
### id获取接口
http://localhost:9949/api/segment/get/{key}
### 健康检查
http://localhost:8081/actuator  
http://localhost:8081/actuator/health
# 部署文档
## nacos相关新增配置
name: `leaf.properties`  
group: `leaf`  
type: `properties`  
```
leaf.name=imi-leaf-server
leaf.segment.enable=true
leaf.segment.url=jdbc:mysql://***:3306/leaf?useUnicode=yes&characterEncoding=UTF-8&useSSL=false&useTimezone=true&serverTimezone=GMT%2B8
leaf.segment.username=**
leaf.segment.password=***
leaf.segment.driverClassName=com.mysql.cj.jdbc.Driver

leaf.snowflake.enable=false
#leaf.snowflake.zk.address=
#leaf.snowflake.port=
```
## 环境变量
```
spring.cloud.nacos.server-addr=***
spring.cloud.nacos.config.namespace=***
```
## 数据库
database: leaf  
sql:
```
DROP TABLE IF EXISTS `leaf_alloc`;

CREATE TABLE `leaf_alloc` (
  `biz_tag` varchar(128)  NOT NULL DEFAULT '',
  `max_id` bigint(20) NOT NULL DEFAULT '1',
  `step` int(11) NOT NULL,
  `description` varchar(256)  DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB;
```

