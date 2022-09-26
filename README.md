# Blog简单内容发布平台

RealWorld Project Study   

## 开发日志
1. 创建SpringBoot项目
2. 分析Blog需求，整理后端所提供的接口
    [/sepc/README.md] 整理需要的结构与接口参数
   
3. 实现登陆注册接口,并添加单元测试

### Authentication:

`POST /api/users/login`

Example request body:
```JSON
{
  "user":{
    "email": "jake@jake.jake",
    "password": "jakejake"
  }
}
```

No authentication required, returns a [User](#users-for-authentication)

Required fields: `email`, `password`


### Registration:

`POST /api/users`

Example request body:
```JSON
{
  "user":{
    "username": "Jacob",
    "email": "jake@jake.jake",
    "password": "jakejake"
  }
}
```

No authentication required, returns a [User](#users-for-authentication)

Required fields: `email`, `username`, `password`

4. SpringBoot 配置 jacoco plugins
    未完成，后续配置

5.JWT

什么是JWT？

Json Web Token 

JWT通常，头信息（header），消息体（payload），签名(signature)组成

头信息指定了改JWT使用的签名算法
header = '{"alg:"HS256","typ":"JWT"}'


6. 登陆注册后返回 带token信息返回,
   编写Service 单元测试








