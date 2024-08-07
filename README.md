# Maa4j
一款基于Maa的高可用的安卓模拟器管理端

## 功能
通过Maa管理多个安卓虚拟机。  
具体功能（新功能见TODO）：
- 多用户支持
- Maa管理（部分仍在适配）
- 权限管理
- 多单例管理
- 多设备支持
- 理智监控

## TODO
Maa适配进度：
- [x] 开始唤醒
- [x] 自动公招
- [x] 基建换班
- [x] 获取信用及购物
- [x] 刷理智
- [x] 领取奖励
- [ ] 自动肉鸽
- [ ] 生息演算
- [ ] 自动战斗

Todo:
- [x] 更完备的异常处理机制
- [x] 清晰的API文档
- [x] 管理员权限管理（完善中）
- [x] 日志系统
- [ ] 解决maa截图失败的问题
- [ ] 自定义换号
- [ ] 插队功能
- [ ] 特殊活动设定

## 使用方法
### 发行版
安装java（jdk21及以上版本）  
安装MySQL8，创建用户并新建数据库maa4j  
（如果是MySQL8.0以下的版本，请自行更改数据库方言）  
下载最新发布版，并在同一目录下新建application.yml
```yaml
server:
  port: 8848（端口，随便填）

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: （数据库连接地址）
    username: （数据库用户名）
    password: （对应的密码）
  mvc:
    converters:
      preferred-json-mapper: gson
    pathmatch:
      matching-strategy: ant_path_matcher
  jpa:
    database: mysql
    show-sql: true
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true

springdoc:
  swagger-ui:
    path: /swagger-ui.html
    tags-sorter: alpha
    operations-sorter: alpha
  api-docs:
    path: /v3/api-docs
  group-configs:
    - group: 'default'
      paths-to-match: '/**'
      packages-to-scan: top.chr0nix.maa4j

knife4j:
  enable: true
  setting:
    language: zh_cn

maa4j:
  secret: "maa"（改为任意字符串，可不填）
  use_data: true（使用保存的数据文件）
  ignore_auth: false（忽略认证，仅限开发为true）
  maa_path: （Maa目录地址）
  adb_path: （adb文件地址）
  devices:
    - "localhost:16384"（这里改为自己的设备地址，可不填或填多个）
```
然后运行(记得改为本机地址)：
```shell
java -jar .../maa4j-latest.jar
```
### 源码
将src/main/resources/application.yml中的active字段后改为dev（若已经为dev无需在意）
```yaml
spring:
  profiles:
    active: dev
```
然后将同目录下application-dev.yml按照上述示例补充完整  
编译运行方法自行摸索（建议使用IDE）

### API文档
启动后访问：http://localhost:8848/doc.html  
若需要swagger-ui,请访问：http://localhost:8848//swagger-ui.html



## 友情链接
[Maa](https://github.com/MaaAssistantArknights/MaaAssistantArknights)