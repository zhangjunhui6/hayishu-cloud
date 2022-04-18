# hayishu-cloud

基于SpringCloud2.3的校园二手书交易系统，整合了nacos、feign、springcloud-gateway服务，实现Docker部署和云部署。

前端项目：![hayishu-vue2](https://github.com/zhangjunhui6/hayishu-vue2)

## 软件架构

![软件架构](/static/image/微服务架构.png)

## 系统功能

![系统功能](/static/image/系统功能.png)

## 目录结构

```bash
hayishu-cloud
├─book-service    # 书本微服务
│  └─src          # springboot代码目录
│      └─main
│          ├─java
│          │  └─org
│          │      └─example
│          │          └─book
│          │              ├─config      # 存放配置信息
│          │              ├─controller  # 前端控制器
│          │              ├─mapper      # 数据接口层
│          │              ├─pojo        # 存放实体类
│          │              └─service     # 数据服务层
│          └─resources
├─cart-service    # 购物车微服务
├─club-service    # 书友微服务
├─data  
│  └─mysql        # MySQL数据库
├─docker-compose  
├─feign-api       # 微服务间调用的公共接口
├─gateway         # 网关微服务
├─order-service   # 订单微服务
├─static          
└─user-service    # 用户微服务
```

## Docker部署
1. 容器信息
   
    | 容器名 | 功能 | 端口 | 基础镜像 |
    | :----: | :----: | :----: | :----: |
    | mysql | 数据库 | 3306 | mysql:5.7.25 |
    | nginx | 反向代理服务器 | 80 | nginx:latese |
    | nacos | 服务注册与发现 | 8848 | nacos/nacos-server:latest |
    | gateway | 网关服务 | 10086 | java:8-alpine |
    | user-service | 用户服务 | 8081 | java:8-alpine |
    | club-service | 书友服务 | 8082 | java:8-alpine |
    | book-service | 图书服务 | 8083 | java:8-alpine |
    | cart-service | 购物车服务 | 8085 | java:8-alpine |
    | order-service | 订单服务 | 8086 | java:8-alpine |


2. 部署图
   
   ![Docker部署](/static/image/Docker部署.png)

## 运行演示

1. 登录页面
   
   ![login](/static/image/demo/login.png)

2. 首页
   
   ![index](/static/image/demo/index.jpg)
