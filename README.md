# newsblog

## 导出数据库
在控制台执行
```sql
mysqldump -uroot -p newsblog > newsblog.sql
```

## 导入数据库
首先进入mysql客户端，通过如下命令
```sql
mysql -uroot -p
```

然后通过source命令加news.sql的全路径名，例如
```sql
source E:/IdeaProjects/news/newsblog.sql
```

## 运行项目
需要启动Redis服务和ElasticSearch服务

## 项目介绍
该项目的第一个模块就是登录注册，注册的时候对密码进行了加密，登录的话，为了实现记住密码免登录，专门设计了一个拦截器进行判断，记住密码的核心思路，是通过浏览器的cookie，然后数据库中对应一个字段，每次访问首页都会拦截，并判断cookie，判断完之后，都会放行。

第二个模块就是图片的问题，目前头像的问题解决了，用户可以自定义上传头像，关于图片，我是放到了单独的文件夹，没有跟项目放在一块，在C:\upload下

第三个模块就是写博客了，我用的是markdown编辑器，已经可以写博客了

第四个模块就是关于文章，文章有标签，可用于实现推荐功能，有分类，可以点赞，评论和回复

第五个模块就是消息，当文章被点赞和评论的时候，会收到通知，还有就是管理员可以发布公告

第六个模块是关于用户的，关注，私信等功能更新中...
