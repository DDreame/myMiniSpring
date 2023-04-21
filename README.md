# 关于项目

一个 Spring 核心的实现项目，从 IOC 开始

# 主要来源

[手把手带你写一个 MiniSpring -- 极课时间](https://time.geekbang.org/column/intro/100536701)

# TODO

## IOC 
 - [x] 从 XML 开始构建原始IOC容器
 - [x] 实现 XML 的参数注入
 - [x] 打破循环依赖
 - [x] 实现一个注解
 - [x] 构建工厂体系
 - [x] 实现事件系统
 - [ ] 实现基本类型注入

## MVC

- [x] 使用 Servlet 拦截请求
- [x] IOC 管理 MVC
- [x] 两级缓存，合并 IOC 和 MVC
- [x] 使用 Dispatcher 分别管理
- [x] 实现返回 Json 数据
- [x] 实现使用 String 返回 JSP
- [ ] 实现返回基本类型而非 JSP


## JDBC Template

- [x] 抽取JdbcTemplate
- [x] 使用回调函数简化业务类
- [x] 组合IOC容器
- [x] 组件化SQL参数注入
- [x] SQL 返回结果优化
- [ ] 构建链接池


## AOP

todo...

# Doc

 - feat: 新功能
 - fix: 修复bug
 - docs: 文档修改
 - pref: 性能优化
 - revert: 版本回退
 - ci: CICD 集成相关
 - test: 添加测试代码
 - refactor: 代码重构
 - build: 影响项目构建或依赖修改
 - style: 不影响程序逻辑的代码修改
 - chore: 不属于以上任何类型的其他类型(日常事务