# Spring-solr
本项目基于Apache Solr提供一个根据需求配置好的搜索引擎并导入一些初始数据，再基于Spring框架连接solr实现企业级搜索的功能。主要内容如下：

1.搜索引擎基于Solr5.3.1，官网：http://lucene.apache.org/solr/

2.Solr中已经配置好三个内核，用于连接三种不同的数据源：

  -mongoDB内核用于同步mongoDB的数据进行搜索；
  
  -sakila内核用于搜索导入的Mysql中的默认数据库sakila；
  
  -tika内核用于将本地指定文件夹下的文档（Office或PDF等）导入，进行全文检索，本样例仅提供26个文档供体验。
  
3.已完成Solr和Tomcat服务器的整合，整个文件夹下载后，进入apache-tomcat-8.0.36中bin目录，双击startup.bat即可开始运行；

4.已整合中文分词器IKAnalyzer，官网https://github.com/EugenePig/ik-analyzer-solr5 。在apache-tomcat-8.0.36\webapps\solr\WEB-INF\classes路径下添加了用户自定义的字典my.dic用于扩展分词；

5.mongoDB与solr同步工具使用mongo-connector完成，需在Linux下运行，官网https://github.com/mongodb-labs/mongo-connector

6.Spring后台使用solrJ与solr进行交互；

7.把Tomcat拷贝到D盘根目录下，启动Tomcat后，访问http://localhost:8080/solr/  可进入solr的管理控制台页面，搜索入口访问http://localhost:8080/Spring-solr/search

8.前端暂不提供对sakila和mongoDB的搜索，仅提供了文档的全文检索，要使用数据库的搜索，请进入solr控制台查看效果。

效果图：
 ![输入图片说明](http://git.oschina.net/uploads/images/2016/1116/080927_551753d6_1110335.png "在这里输入图片标题")
![输入图片说明](http://git.oschina.net/uploads/images/2016/1116/080940_39753846_1110335.jpeg "在这里输入图片标题")

### 附录：个人作品索引目录（持续更新）

#### 基础篇:职业化，从做好OA系统开始
1. [SpringMVC,Mybatis,Spring三大框架的整合实现增删改查](https://gitee.com/shenzhanwang/SSM)
2. [Struts2,Hibernate,Spring三大框架的整合实现增删改查](https://gitee.com/shenzhanwang/S2SH)
3. [Spring,SpringMVC和Hibernate的整合实现增删改查](https://gitee.com/shenzhanwang/SSH)
4. [Spring平台整合activiti工作流引擎实现OA开发](https://gitee.com/shenzhanwang/Spring-activiti)
5. [Spring发布与调用REST风格的WebService](https://gitee.com/shenzhanwang/Spring-REST)
6. [Spring整合Apache Shiro框架，实现用户管理和权限控制](https://gitee.com/shenzhanwang/Spring-shiro)
7. [使用Spring security做权限控制](https://gitee.com/shenzhanwang/spring-security-demo)
8. [Spring整合Jasig CAS框架实现单点登录](https://gitee.com/shenzhanwang/Spring-cas-sso)
#### 中级篇：中间件的各种姿势
9. [Spring连接mongoDB数据库实现增删改查](https://gitee.com/shenzhanwang/Spring-mongoDB)
10. [Spring连接Redis实现缓存](https://gitee.com/shenzhanwang/Spring-redis)
11. [Spring连接图存数据库Neo4j实现增删改查](https://gitee.com/shenzhanwang/Spring-neo4j)
12. [Spring平台整合消息队列ActiveMQ实现发布订阅、生产者消费者模型（JMS）](https://gitee.com/shenzhanwang/Spring-activeMQ)
13. [Spring整合消息队列RabbitMQ实现四种消息模式（AMQP）](https://gitee.com/shenzhanwang/Spring-rabbitMQ)
14. Spring框架的session模块实现集中式session管理（未开源）
15. [Spring整合websocket实现即时通讯](https://gitee.com/shenzhanwang/Spring-websocket)
16. 使用Spring boot整合mybatis,rabbitmq,redis,mongodb实现增删改查（未开源）
17. [Spring MVC整合FastDFS客户端实现文件上传](https://gitee.com/shenzhanwang/Spring-fastdfs)
18. 23种设计模式，源码、注释、使用场景（未开源）
19. 使用ETL工具Kettle的实例（未开源）
20. Git指南和分支管理策略（未开源）
#### 高级篇：架构之美
21. [zookeeper原理、架构、使用场景和可视化](https://gitee.com/shenzhanwang/zookeeper-practice)
22. 使用ubuntu+apache+SVN+SVNadmin+maven+Nexus+Hudson搭建持续集成环境（未开源）
23. 使用jenkins+centos+git+maven搭建持续集成环境自动化部署分布式服务（未开源）
24. Spring框架整合dubbo框架实现分布式服务治理（SOA架构）（未开源）
25. Spring框架整合dubbox实现微服务架构（MSA架构）（未开源）
26. 使用Spring Cloud实现微服务架构（MSA架构）（未开源）
27. 使用FastDFS搭建分布式文件系统（高可用、负载均衡）（未开源）
28. 搭建高可用nginx集群和Tomcat负载均衡（未开源）
29. 搭建可扩展的ActiveMQ高可用集群（未开源）
30. 实现Mysql数据库的主从复制、读写分离、分表分库、负载均衡和高可用（未开源）
31. 搭建高可用redis集群实现分布式缓存（未开源）
32. [Spring整合SolrJ实现全文检索](https://gitee.com/shenzhanwang/Spring-solr)
#### 特别篇：分布式事务和并发控制
33. 基于可靠消息最终一致性实现分布式事务（activeMQ）（未开源）
34. 使用TCC框架实现分布式事务（dubbo版）（未开源）
35. 使用TCC框架实现分布式事务（Spring Cloud版）（未开源）
36. 决战高并发：数据库锁机制和事务隔离级别的使用（未开源）
37. 决战高并发：使用redis实现分布式锁（未开源）
38. 决战高并发：使用zookeeper实现分布式锁（未开源）
39. 决战高并发：Java多线程编程实例（未开源）
40. 决战高并发：使用netty实现高性能NIO通信（未开源）

### 捐赠区
![输入图片说明](https://images.gitee.com/uploads/images/2018/0719/154323_12a5c89c_1110335.jpeg "mm_facetoface_collect_qrcode_1531986023521.jpg")
