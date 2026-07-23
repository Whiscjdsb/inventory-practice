# inventory-practice

基于 Spring Boot 开发的库存管理练习项目。

## 已实现功能

- 商品增删改查
- 库存增加与扣减
- JWT 登录认证
- 用户权限控制
- Redis 商品缓存
- 统一响应与异常处理
- Swagger / OpenAPI 接口文档
- Actuator 健康检查

## 技术栈

- Java 17
- Spring Boot
- MyBatis-Plus
- MySQL
- Redis
- Spring Security
- JWT

## 环境要求

- Java 17
- Maven 3.9+
- MySQL 8
- Redis

MySQL 中需要存在 `inventory` 数据库和项目所需的数据表，Redis 默认连接 `127.0.0.1:6379`。

## 环境变量

启动项目前需要配置：

| 变量名 | 作用 | 是否必填 |
| --- | --- | --- |
| `DB_PASSWORD` | MySQL 密码 | 是 |
| `JWT_SECRET` | Base64 格式的 JWT 密钥，解码后至少 32 字节 | 是 |
| `SERVER_PORT` | 服务端口 | 否，默认 `8080` |
| `DB_URL` | MySQL 地址 | 否 |
| `DB_USERNAME` | MySQL 用户名 | 否，默认 `root` |

Git Bash 可以使用下面的命令生成临时 JWT 密钥：

```bash
export JWT_SECRET="$(openssl rand -base64 32)"
```

不要把真实密码或 JWT 密钥提交到 Git。

## 构建与运行

运行测试：

```bash
mvn test
```

打包项目：

```bash
mvn package
```

运行生成的 JAR：

```bash
java -jar target/inventory-practice-0.0.1-SNAPSHOT.jar
```

启动成功后可以访问：

- Swagger：`http://localhost:8080/swagger-ui.html`
- 健康检查：`http://localhost:8080/actuator/health`

## 后续计划

- 补充数据库初始化 SQL
- 增加更多核心自动化测试
- 学习 Docker 容器化部署
