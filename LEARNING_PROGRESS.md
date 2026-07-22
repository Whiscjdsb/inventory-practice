# inventory-practice 学习进度

更新时间：2026-07-21（JWT 登录认证主链路已完成）

## 当前技术栈

- Java 17
- Spring Boot 3.2.5
- MyBatis-Plus 3.5.5
- MySQL
- Lombok
- Jakarta Validation
- Postman
- JUnit 5、Mockito
- Swagger / OpenAPI（springdoc）
- Spring Security
- JJWT 0.13.0

## 已完成

- 创建 `product` 商品表并理解主要字段类型和约束。
- 创建 `Product` Entity 和 `ProductMapper`。
- 完成商品新增、根据 ID 查询、修改、删除。
- 完成统一响应 `ApiResponse<T>`，理解泛型 `T` 的基本作用。
- 完成 Request DTO 参数校验和校验异常处理。
- 完成 `BusinessException` 与 `GlobalExceptionHandler`。
- 完成商品列表、名称模糊搜索和分页查询。
- 完成分页参数边界检查。
- 完成商品上架、下架状态修改。
- 完成基础扣库存，并改造成带 `stock >= quantity` 条件的原子 SQL。
- 扣库存 SQL 已增加 `status = 1`，下架商品不能扣库存。
- 完成 `ProductVO` 和 `ProductVO.fromEntity()`。
- 新增、单个查询、修改、列表、分页和状态接口已统一返回 `ProductVO`。
- VO 改造已通过 Maven 测试：`BUILD SUCCESS`，1 个测试通过。
- 创建 `stock_operation` 库存记录表。
- 创建 `StockOperation` Entity 和 `StockOperationMapper`。
- 已把 `StockOperationMapper` 通过构造方法注入 `ProductService`。
- 扣库存成功后会向 `stock_operation` 插入一条 `operation_type = 2` 的出库记录。
- `ProductService.deductStock()` 已添加 `@Transactional`，完成异常回滚测试。
- 已理解事务的基本规则：方法正常结束提交，抛出运行时异常回滚。
- 创建 `StockOperationVO` 和 `StockOperationVO.fromEntity()`，将操作类型转换为“入库/出库”文字。
- 完成根据商品 ID 查询库存操作记录，并按照记录 ID 倒序排列。
- 已理解 `queryWrapper` 保存查询条件，`List<StockOperation>` 保存查询结果。
- 创建 `AddStockRequest`，完成入库数量的 `@NotNull` 和 `@Positive` 校验。
- 在 `ProductMapper` 中完成原子增加库存 SQL：`stock = stock + quantity`。
- 完成商品入库 Service 和 Controller，入库成功后写入 `operation_type = 1` 的记录。
- 入库方法已添加 `@Transactional`，保证增加库存和保存操作记录一起提交或回滚。
- 已通过 Postman 验证：3 号商品库存从 1 增加到 6，同时生成入库记录。
- 已理解 MySQL 自增 ID 在事务回滚或记录删除后可能出现跳号，这是正常现象。
- 已独立复述 Controller → Service → Mapper → 数据库 的请求方向，以及 数据库 → Entity → VO → ApiResponse 的返回方向。
- 已理解 Service 层存在的意义：承载业务逻辑、管理事务、避免 Controller 直接调用 Mapper。
- 已理解 VO 的作用：字段过滤、命名转换、状态值翻译（status → statusText、operation_type → operationTypeText）、隔离数据库表结构。
- 已能独立分析扣库存 SQL 中 `affectedRows == 0` 的三种原因：商品不存在、库存不足、商品下架。
- 已能区分 `ApiResponse<T>` 中 T 的类型：单个对象是 `ProductVO`，列表是 `List<ProductVO>`，无返回是 `Void`。
- 已能用基础 for 循环写 `List<Entity>` 转 `List<VO>`，并理解 Stream 版本的等价逻辑。
- 已理解 for 循环版本的意义：是 Stream 的本质，复杂转换和调试时更灵活。
- 已理解 `RuntimeException` 是运行时异常父类，`@Transactional` 默认遇到运行时异常才回滚；`BusinessException` 继承 `RuntimeException` 是为了让事务回滚且不强制 try-catch。
- 已独立从空白编写 `searchProducts` 接口（Controller + Service），并修复了 `>` 括号缺失、`@RequestParam(required = false)` 缺失等问题。
- 已给 Service 里已有的 `getLowStockProducts` 补上 Controller 接口，并验证通过。
- 已识别并整理重复代码：删除 `searchProducts`，把排序合并到 `getProductList` 里；删除 Controller 的 `/search` 接口。
- 已往 product 表插入 10 条测试数据（3 个手机、3 个笔记本、耳机、键盘、鼠标、显示器）。
- 已通过 Postman 验证模糊查询和 low-stock 接口均能正常工作。
- 已完成查询参数 `trim()` 处理，理解后端不应信任前端输入的原则。
- 已完成多条件动态查询接口：按名称 + 价格区间查询，每个条件独立判空。
- 已正确使用 `BigDecimal.compareTo()` 比较价格范围合法性。
- 已完成批量删除接口，使用 `@RequestBody List<Long>` 接收参数、`deleteBatchIds` 执行批量删除。
- 已掌握删除前先查、查完再删的思路，避免"先删后查拿不到数据"。
- 已完成 `@RequestBody` 接收 JSON 数组的基本用法。
- 已完成 `saveBatch` 批量保存，理解与 for 循环 insert 的差异（攒一批、一次 SQL）。
- 已将 `ProductService` 继承 `ServiceImpl<ProductMapper, Product>`，获得 IService 通用方法。
- 已给批量新增方法添加 `@Transactional`，理解批量操作中部分失败应全部回滚。
- 已理解 `@Valid` 对 `List<@Valid X>` 中的元素不生效，需在 Service 层手动校验。
- 已在 `createProducts` 循环中增加 name 判空校验。
- 已学习 `@Transactional(rollbackFor = Exception.class)`，理解受检异常默认不回滚、加 rollbackFor 后强制回滚。
- 已完成商品逻辑删除与恢复验证：删除后数据库记录仍存在且 `is_deleted = 1`，恢复后改回 `0`。
- 已理解 `@TableLogic` 会让普通查询自动过滤已逻辑删除的数据，自定义恢复 SQL 可以直接修改删除标记。
- 已完成未删除商品总数接口，掌握 `productMapper.selectCount(null)`。
- 已完成上架商品数量接口，掌握通过 `LambdaQueryWrapper<Product>` 添加 `status = 1` 后执行 `selectCount(queryWrapper)`。
- 已通过商品 4 的下架、统计和重新上架验证：下架不影响商品总数，只影响上架数量。
- 已把批量删除前的逐个 `selectById` 查询优化为 `selectBatchIds(ids)`，减少数据库交互次数。
- 已学习 `List` 允许重复、`Set` 不允许重复，以及使用 `HashSet` 和 `size()` 检测重复 ID。
- 已为批量删除补充空列表、非法 ID、重复 ID 和不存在 ID 的校验。
- 已学习把查询到的 `Product` ID 放入 `Set<Long>`，再通过 `contains()` 精确找出不存在的请求 ID。
- 已通过商品 23、24 验证正常批量逻辑删除，通过商品 4 与 999999 验证缺失 ID 不会误删存在的商品。
- 已理解批量删除没有响应数据时应返回 `ApiResponse<Void>`；曾误写为 `ApiResponse<Null>`，现已改正。
- 已理解 Java 的 `||` 短路判断：`id == null || id < 1` 会先拦截 null，避免空指针异常。
- 已发现并修复 `if (...) ;` 多余分号导致异常无条件执行的问题。
- 已调整批量删除校验顺序为：空列表 → ID 合法性 → 重复 ID → 批量查询 → 存在性检查 → 批量删除。
- 已把批量删除 Controller 的错误类型 `ApiResponse<Null>` 修正为 `ApiResponse<Void>`。
- 已独立尝试“查询已上架且库存为 0 的商品”功能，并在纠错后完成 Service、Controller 和 Postman 验证。
- 已进一步区分单个对象与列表返回类型：单个商品用 `ProductVO`，多个商品用 `List<ProductVO>`。
- 已理解 MyBatis-Plus 的 `eq` 表示等于、`lt` 表示小于；查询库存为 0 应使用 `eq(Product::getStock, 0)`。
- 已理解 `selectList` 返回 `List<Product>`，需要通过 `fromEntity()` 转换为 `List<ProductVO>`。
- 已理解 `stream().map(ProductVO::fromEntity).toList()` 等价于遍历、转换和收集；Stream 目前以能看懂和会套用模板为目标。
- 已通过创建库存为 0 的测试商品验证 `/api/products/out-of-stock`；曾因 Postman 未从 POST 切换为 GET 导致误判。
- 已开始学习 MySQL `EXPLAIN`，完成缺货查询的首次执行计划分析：`type = index`、`key = PRIMARY`、`rows = 21`、`Using where; Backward index scan`。
- 已新建独立错误复盘文件 `LEARNING_ERRORS.md`，后续遇到高频错误继续追加。
- 已为缺货查询创建联合索引 `(is_deleted, status, stock, id)`，执行计划由扫描主键索引改善为使用联合索引 `idx_product_deleted_status_stock_id`。
- 已理解联合索引最左前缀：查询跳过最左列时可能无法有效利用索引；逻辑删除字段会影响实际 SQL 和索引设计。
- 已学习 B+Tree 的多叉、有序、叶子节点保存数据并形成链表等特点，以及 MySQL 使用它支持等值、范围和排序查询的原因。
- 已理解 InnoDB 是 MySQL 的存储引擎，支持事务、行锁、外键、崩溃恢复和 MVCC。
- 已学习 `REPEATABLE READ`、`START TRANSACTION`、`SELECT ... FOR UPDATE` 和行锁，并完成两连接锁等待实验。
- 已理解前缀模糊查询和 `%关键词%` 的差异；前导 `%` 可能让普通 B+Tree 索引失效并出现 `type = ALL`。
- 已为 `ProductService` 编写 Mockito 单元测试，覆盖缺货商品 Entity 转 VO 和库存不足时不写库存记录；测试运行成功。
- 已修复错误导入 JDK 内部 `Constraint`、JUnit 版本冲突和重复测试依赖，能从日志底部定位 `Caused by`。
- 已引入 Swagger / OpenAPI，能通过 `/swagger-ui.html` 查看和调试接口，理解 Swagger 与 Postman 的用途差异。
- 已创建 `sys_user` 表、`SysUser` Entity、`SysUserMapper`、注册请求 DTO 和密码编码配置。
- 已完成用户注册：用户名判重、BCrypt 密码加密、默认角色与状态设置；注册和重复用户名验证成功。
- 已理解敏感配置使用 `${环境变量名}`，JWT 密钥和数据库密码不应写入源码或提交 Git。
- 已引入 JJWT，完成 `JwtTokenProvider`：生成 Token、设置用户名/用户 ID/角色、过期时间和签名，并能解析、校验 Token 和取得用户名。
- 已完成登录 Service 与 Controller：用户查询、统一用户名或密码错误、密码匹配、禁用状态判断和 `LoginVO` 返回。
- 已通过正确密码和错误密码测试：正确登录返回 JWT，错误密码返回 401。
- 已引入 Spring Security，理解 `OncePerRequestFilter`、`Authorization: Bearer`、`SecurityContextHolder` 和无状态认证流程。
- 已完成 JWT 过滤器与安全规则：注册、登录、Swagger 公开，其他接口必须认证；商品 4 已验证“带 Token 返回 200、不带 Token 返回 401”。

## 当前正在学习

目标：以尽快达到 Java 后端开发实习要求为导向，在 `inventory-practice` 中真正掌握核心知识，而不是只跟着代码过一遍。

学习定位：

- `inventory-practice` 是当前主线练习项目，Controller、Service 和核心业务判断优先由学习者手写。
- `springboot-practice` 中的 JWT、Redis、RabbitMQ、AOP 和微服务等内容以前只跟着做过一遍，暂不视为已经掌握，只作为旧代码参考。
- 不再反复堆相似 CRUD，也不过度研究低价值边界细节；每个功能做到理解原理、写出核心代码、验证成功并能用于面试说明。
- Java 语法出现实际错误时再针对性讲解，重点补集合、异常、泛型和基础代码组织能力。

当前下一步（按优先级从高到低）：

1. 完成受保护的 `/api/auth/me` 接口，从 Spring Security 的 `Authentication` 取得当前用户名。
2. 把 JWT 中的 `role` 转换为 Spring Security 权限，理解认证（是谁）与授权（能做什么）的区别。
3. 为注册和登录补少量核心单元测试，不堆重复边界测试。
4. 学习 Redis 的高频用途，优先做商品查询缓存或登录相关的简单实战。
5. 学习 Git 基础协作流程，整理项目 README、简历描述和项目讲解。
6. 继续巩固 Java 基础与常见 Java/Spring Boot/MySQL 面试题。

## 当前真实掌握程度（2026-07-21）

- 能理解 `Controller → Service → Mapper → 数据库` 的基本调用方向。
- 能在提示下完成基础 CRUD、条件查询和统一响应类型。
- 已接触分页、事务、逻辑删除、批量操作和原子库存更新，但仍需通过独立编写巩固。
- Java 集合和语法细节还不稳定，需要结合真实业务继续练习。
- 已能从需求开始尝试组织完整的小功能，但返回类型、查询条件和 Entity/VO 转换仍需要提示纠正。
- 已开始接触 `EXPLAIN`，能在讲解下认识 `type`、`key`、`rows` 和 `Extra`，尚未独立掌握索引设计。
- 已在讲解下完成联合索引、事务、行锁和 B+Tree 学习，能理解核心用途，但仍需独立复述和再次实战巩固。
- 已在讲解下完成注册、登录、JWT 生成与校验、过滤器和接口保护的完整链路。
- 能说明 `Authorization: Bearer`、JWT 校验、`SecurityContext` 和 401 拦截的基本流程；Spring Security 固定 API 目前以理解和会修改模板为目标，不要求默写。
- 能从长日志最底层 `Caused by` 定位环境变量缺失和临时目录权限问题，但本地运行配置仍需继续熟悉。

## 后端实习导向

优先掌握：

- Java 面向对象、集合、异常、泛型和常用并发基础。
- Spring Boot 分层、依赖注入、参数校验和全局异常处理。
- MyBatis-Plus、SQL、分页、批量操作和动态查询。
- MySQL 索引、事务、锁和执行计划。
- JWT、Redis、接口文档、单元测试和 Git。
- 能清楚讲解请求链路、事务回滚、原子扣库存和项目难点。

暂时降低优先级：

- 前端页面和 JSP。
- 冷门 Java 语法和复杂框架源码。
- 过度复杂的分布式架构。
- 价值较低的重复 CRUD 和过多边界测试。

## 当前掌握要求

需要逐渐做到能自己手写：

- Entity、Request DTO、VO 的基本结构。
- Controller 接收参数并调用 Service。
- Service 中的查询、判空、业务判断、更新和异常抛出。
- 基础 MyBatis-Plus CRUD 调用。
- 简单 SQL，以及原子扣库存中 `stock >= quantity` 的意义。
- `fromEntity()` 的基础字段转换。
- 根据入库或出库计算 `beforeStock` 和 `afterStock`。
- 根据 Mapper SQL 条件分析 `affectedRows` 的含义。
- 使用 `queryWrapper` 组织基础筛选和排序条件。

可以查资料或复制模板：

- 框架配置类。
- 全局异常处理器的固定结构。
- `@Update`、`@Param` 等 Mapper 注解的包名和固定格式。
- Stream、方法引用和分页 VO 转换。
- `@Transactional` 的 import 和固定用法，但必须理解事务为什么存在。

## 本轮复盘结论

已经理解：

- `Controller -> Service -> Mapper -> 数据库` 的请求方向。
- `数据库 -> Entity -> VO -> ApiResponse` 的返回方向。
- `@PathVariable` 从请求路径取值，`@RequestBody` 从 JSON 请求体取值。
- DTO 注解检查请求参数，数据库记录是否存在需要由 Service 查询判断。
- `affectedRows` 只表示 SQL 影响的行数，不包含最新商品库存。
- `ProductMapper` 操作 `product` 表，`StockOperationMapper` 操作 `stock_operation` 表。
- Entity 面向数据库，VO 面向前端响应。

## 常见错误汇总（2026-07-19 更新）

> 以下错误均为实际编码中出现的，下次遇到时优先检查这几项。

### 1. 泛型括号不匹配

**示例：** `ApiResponse<List<ProductVO>`（少了一个 `>`）
**改正：** `ApiResponse<List<ProductVO>>`
**规律：** `List<X>` 外面再套一层泛型，关闭括号数量是 `<` 数量的两倍。

### 2. 方法名拼写错误

**示例：** `deletePoducts`（少了一个 r）
**改正：** `deleteProducts`
**原则：** 写完方法名后看一眼。

### 3. private vs public

**示例：** Controller 方法写成 `private`
**改正：** Controller 方法必须是 `public`
**原因：** Spring 通过反射调用 Controller 方法，`private` 方法外部不可见，会报错。

### 4. 先删后查，查不到数据

**场景：** 批量删除时先 `deleteById` 逐个删，再去查，当然查不到。
**改正：** 先查出来再用 `deleteBatchIds` 删。

### 5. 查询条件没判空就加到 queryWrapper

**示例：**
```java
queryWrapper.like(Product::getName, name);   // name 可能是 null
queryWrapper.ge(Product::getPrice, minPrice); // minPrice 可能是 null
```
**改正：** 每个可选参数都要 `if (... != null)` 判断后再加条件。

### 6. 查询参数没有 trim

**示例：** name 参数前后有空格，导致 `LIKE '% 手机%'` 匹配不到
**改正：** 字符串参数进查询前做 `name.trim()`

### 7. Variable name conflict

**示例：**
```java
List<ProductVO> productVO = ...;    // 外面的列表
ProductVO productVO = new ...;      // 里面的单个对象——冲突！
```
**改正：** 列表用 `productVOList` 或 `vos`，单个用 `vo`

### 8. VO 里多加了不存在的方法/字段

**示例：** `productVO.setStockOperationList(getStockOperationList(...))`——ProductVO 里根本没有这个方法
**改正：** 写 setter 前确认 VO 类里确实有这个字段

### 9. @RequestParam 忘写 required = false

**示例：** `@RequestParam String name` — 不传 name 时直接 400 报错
**改正：** 可选参数要加 `@RequestParam(required = false)` 或者设 `defaultValue`

### 10. isBlank 和 isEmpty 的区分

- `isEmpty()`：字符串长度为 0 时返回 true（`""`）
- `isBlank()`：字符串为空或全是空白时返回 true（`""`、`"  "`、`"\t"`）
- 参数 trim 前后：如果用了 `trim()` 则 `isEmpty()` 和 `isBlank()` 效果一样
- **推荐用 `isBlank()`**，因为你在 trim 之前就可能需要判断

### 11. BigDecimal 不能用 `>` `<` 比较

**示例：** `minPrice > 0` — 编译通过但逻辑不对
**改正：** `minPrice.compareTo(BigDecimal.ZERO) > 0`

### 12. 查询方法不需要 @Transactional

**原则：** 只有写操作（insert、update、delete）才加 `@Transactional`。纯查询方法加了反而占连接资源。

### 13. Controller 路径不能冲突

**示例：** 项目中已有 `@GetMapping`（无路径），又加了一个 `@GetMapping("/list")`，两个接口功能类似容易混淆。
**原则：** 每个路径唯一，语义清晰。

### 14. List 类型声明错误

**示例：** `List<ProductVO> products = new ArrayList<>();` 然后 `products.add(product)`（product 是 Product 类型）
**改正：** `List<Product> products = new ArrayList<>();`
**原则：** `List<>` 里的类型要和 `add()` 的元素类型一致。

### 15. 硬编码数据替代请求数据

**示例：** 批量新增时用了 `product.setName("商品" + id)` 而不是 `request.getName()`
**改正：** 永远从请求参数取值，不要自己硬编码。

### 16. 方法调用错误

**示例：** 批量新增接口里调了 `productService.deleteProducts(ids)`（调成了删除）
**改正：** 写方法调用前确认方法名正确。

### 17. @Valid 对 List 元素不生效

**场景：** `@RequestBody List<@Valid CreateProductRequest>` 不会校验列表里每个元素的字段
**改正：** 在 Service 循环中手动判断参数合法性
**原则：** 不能依赖 `@Valid` 拦截 List 元素，Service 层必须自己校验。

### 18. 不要在循环里对 null 字段 insert

**场景：** MyBatis 默认跳过 null 字段，如果数据库字段是 NOT NULL 且无默认值，会报 DataIntegrityViolationException
**改正：** 入参校验在前端拦住 null，或在 Entity 设值阶段给默认值。

## 仍需加强（旧记录）

- 目前可以在提示下完成业务代码，还需要练习从空白独立组织完整方法。
- 不要直接复制另一项业务的异常判断；入库和扣库存的失败条件不同。
- 区分集合类型和元素类型，例如 `List<StockOperationVO>` 与 `StockOperationVO`。
- 继续熟悉泛型的完整类型，例如 `ApiResponse<List<StockOperationVO>>` 中的 `T` 是整个 `List<StockOperationVO>`。
- Stream 和方法引用目前以能看懂、会调用为目标，不要求脱离提示手写。

## 已知非阻塞警告

- `pom.xml` 中重复声明了 `spring-boot-starter-test`。
- 用户目录下的 `.m2/settings.xml` 根元素格式不规范。
- 这两个警告目前不影响项目编译，后续可以单独清理。

## 新任务继续方式

新任务开始时先说明：

> 先读取 AGENTS.md、LEARNING_PROGRESS.md 和 LEARNING_ERRORS.md，然后从“当前下一步”继续。
