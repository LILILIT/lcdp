# lcdp

本仓库当前包含一个基于 Spring Boot 的微服务示例模块 **P3**，集成了 Spring Cloud Alibaba Nacos（配置中心与服务注册发现）、MyBatis 和 MySQL。

## P3 模块

### 技术栈

| 组件 | 版本 |
| --- | --- |
| Java | 8 |
| Spring Boot | 2.4.0 |
| MyBatis Spring Boot Starter | 2.1.4 |
| MySQL Connector/J | 8.x（由 Spring Boot 管理） |
| Spring Cloud Alibaba Nacos Config | 2021.1 |
| Spring Cloud Alibaba Nacos Discovery | 2021.1 |
| Spring Cloud Starter Bootstrap | 3.1.3 |

### 目录结构

```
lcdp/
└── P3/
    ├── pom.xml
    └── src/main/
        ├── java/org/example/
        │   ├── P3Main.java              # 启动类与 REST 接口
        │   └── mapper/TestMapper.java   # MyBatis Mapper 接口
        └── resources/
            ├── application.properties   # 端口、数据源、MyBatis 配置
            ├── bootstrap.properties     # Nacos 配置
            └── mapper/TestMapper.xml    # MyBatis SQL 映射
```

### 环境要求

- JDK 8+
- Maven 3.6+
- MySQL（默认 `localhost:3306`，数据库 `study`）
- Nacos Server（默认 `127.0.0.1:8848`）

### 配置说明

- `application.properties`：服务端口 `6666`、MySQL 数据源、MyBatis mapper 位置。
- `bootstrap.properties`：Nacos 服务地址、应用名 `provider`、命名空间与配置项。
- 配置项 `aa.bb` 通过 Nacos 配置中心下发（见 `@Value("${aa.bb}")`），需在 Nacos 中预先配置。

### 构建与运行

```bash
cd P3
mvn clean package
java -jar target/P3-1.0-SNAPSHOT.jar
```

### 接口说明

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/HealthCheck` | 健康检查，返回 `aa.bb` 与端口拼接结果 |
| POST | `/testSql` | 参数 `sqlId` 为 `selectAll` 或 `selectOne` 时查询 `students` 表 |

### 数据库

MyBatis 的 `selectAll` 与 `selectOne` 查询 `students` 表，运行前需在数据库 `study` 中准备好该表。
