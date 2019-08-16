## Java 论坛项目


## 资料
[Spring 文档](https://spring.io/guides)  
[Spring web](https://spring.io/guides/gs/serving-web-content/)  
[okhttp](https://square.github.io/okhttp/)  
[数据库教程](https://www.runoob.com/mysql/mysql-tutorial.html)  
[Github OAuth](https://developer.github.com/apps/building-github-apps/)  
[thymeleaf模版官方教程](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)  
[MyBatis Generator](http://www.mybatis.org/generator/)  
[Mysql菜鸟教程](https://www.runoob.com/mysql/mysql-tutorial.html) 

## 工具
[快速搭建前端框架](https://v3.bootcss.com/getting-started/) 
官网下载 Bootstrap源码，并加入到resource/static目录中 

[Flyway数据库整合](https://flywaydb.org/getstarted/) 

## 脚本
```bash
[数据库迁移脚本]
mvn flyway:migrate
[mybatis-generatory运行脚本]
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

[github push脚本]
git status
git add .
git commit -m "..."
git push

```