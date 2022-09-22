# Blog简单内容发布平台

## 开发日志
1. 创建SpringBoot项目
2. 分析Blog需求，整理后端所提供的接口
    [/sepc/README.md] 整理需要的结构与接口参数
   
3. 实现登陆注册接口


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

