# Spring-elastic_search

### 介绍
Spring boot整合elastic search 6.8.1实现全文检索。主要包含以下特性：

1. 全文检索的实现主要包括构建索引、高级搜索、文本分词三个模块；
2. 索引的构建有增量更新和全量更新，一般第一次全量更新，以后增量更新；
3. 使用elasticsearch-rest-high-level-client来操作elasticsearch，构建索引时，根据实际情况考虑哪些字段需要分词，哪些不需要分词，这会影响搜索结果。当构建索引和搜索时，都需要经过“分析”，而分词是分析的一个环节。
![输入图片说明](https://images.gitee.com/uploads/images/2019/0805/082846_8cf33cda_1110335.png "微信截图_20190805082826.png")
4. 高级搜索实现了以下几种：
    - 普通查询,先分词再精准搜索：matchQuery
    - 精准查询,不分词直接搜索：termQuery
    - 模糊查询,搜索字符串接近的词：fuzzyQuery
    - 布尔查询,使用布尔运算组合多个查询条件：boolQuery
    - 范围查询：rangeQuery
    - 前缀查询：prefixQuery
    - 通配符查询：wildcardQuery
    - 多字段搜索,指定多个字段进行搜索:multi_match 
    - 全字段搜索，es自带的全字段搜索:query_string 
    - 经纬度搜索:distanceQuery
    - 日期分面搜索,使用聚集实现，统计每个区间文档的数目:dateHistogramAggregation
5. 文本分词使用了IK分词器：https://github.com/medcl/elasticsearch-analysis-ik
6. 原始数据提交到倒排索引中以前，es可以对原始数据进行一系列的转换操作，这个过程叫做分析。一个完整的分析过程，要经过大于等于0个字符过滤器，一个分词器，大于等于0个分词过滤器组成。在搜索的时候，根据搜索方法的不同也可以选择是否进行经过分析过程。通常match搜索要经过分析，term搜索则不用。

7. swagger入口：http://localhost:8080/swagger-ui.html
### 文档间的关系
- 使用对象存储文档间的关系，仅适用于一对一的场景。因为存储一对多的时候，搜索会出现跨对象的匹配，影响结果的准确性。
- 嵌套类型可以存储一对多的文档关系，但是把所有子表的数据塞到父表中，会降低系统的性能。子文档的更新需要重建整个索引。
- 定义父子关系用于处理一对多的文档关系，在子文档中添加一个_parent字段指向父文档，相当于一个外键。父子文档会被路由到
  同一个分片，查询时需要将文档进行连接，效率低于嵌套，但是修改文档时索引不用重建。
- 反规范化通过复制数据来避免表连接，但是需要更多空间。反规范化是唯一能用于解决多对多的表关系，可以将多对多转化为多个
  一对多。由于数据被复制，导致聚集和查询计算结果拥有重复数据导致结果不准确。
### 集群搭建
我们在centos7搭建一个三台机器的elastic search集群，ip分别为172.16.3.151，172.16.3.152，172.16.3.153.
1. 关闭防火墙和selinux
2. 下载rpm包，运行rpm -ivh elasticsearch-6.8.1
3. 修改每个节点的elasticsearch.yml，在/etc/elasticsearch目录下

```
cluster.name: wsz
# 节点名，根据节点的不同给与不同名字
node.name: hadoop1
# 根据节点的实际ip填写
network.host: 172.16.3.151
# 节点的ip列表
discovery.zen.ping.unicast.hosts: ["172.16.3.151", "172.16.3.152", "172.16.3.153"]
# 控制集群在达到多少个节点之后才会开始复制分片，一般为集群节点数目
gateway.recover_after_nodes: 3
```
4. 每个节点运行systemctl start elasticsearch.service
5. 查看集群状态，访问http://172.16.3.151:9200/_cluster/state ，显示有三个节点，搭建成功。
![输入图片说明](https://images.gitee.com/uploads/images/2019/0814/152002_3517b712_1110335.png "微信截图_20190814151942.png")

### 效果图
![输入图片说明](https://images.gitee.com/uploads/images/2019/0812/114854_6715f7c0_1110335.png "QQ截图20190812114810.png")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0812/114903_bf22e6dd_1110335.png "QQ截图20190812114831.png")
### 附录：个人作品索引目录（持续更新）

#### 基础篇:职业化，从做好OA系统开始
1. [SpringMVC,Mybatis,Spring三大框架的整合实现增删改查](https://gitee.com/shenzhanwang/SSM)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
2. [Struts2,Hibernate,Spring三大框架的整合实现增删改查](https://gitee.com/shenzhanwang/S2SH)
3. [Spring,SpringMVC和Hibernate的整合实现增删改查](https://gitee.com/shenzhanwang/SSH)
4. [Spring平台整合activiti工作流引擎实现OA开发](https://gitee.com/shenzhanwang/Spring-activiti)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
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
14. Spring框架的session模块实现集中式session管理 [购买]
15. [Spring整合websocket实现即时通讯](https://gitee.com/shenzhanwang/Spring-websocket)![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
16. 使用Spring boot整合mybatis,rabbitmq,redis,mongodb实现增删改查 [购买]
17. [Spring MVC整合FastDFS客户端实现文件上传](https://gitee.com/shenzhanwang/Spring-fastdfs)
18. 23种设计模式，源码、注释、使用场景 [购买] 
19. [使用ETL工具Kettle的实例](https://gitee.com/shenzhanwang/Kettle-demo)
20. Git指南和分支管理策略 [购买]
21. 使用数据仓库进行OLAP数据分析（Mysql+Kettle+Zeppelin） [购买]
![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
#### 高级篇：架构之美
22. [zookeeper原理、架构、使用场景和可视化](https://gitee.com/shenzhanwang/zookeeper-practice)
23. Spring boot整合Apache dubbo v2.7实现分布式服务治理（SOA架构） [购买] ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
24. 使用Spring Cloud实现微服务架构（MSA架构）![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")  
> -- vDalston.SR5 [购买]  
-- vFinchley.SR2 [购买]
25. 使用jenkins+centos+git+maven搭建持续集成环境自动化部署分布式服务 [购买] 
26. 使用docker+compose+jenkins+gitlab+spring cloud实现微服务的编排、持续集成和动态扩容 [购买]
27. 使用FastDFS搭建分布式文件系统（高可用、负载均衡）[购买]
28. 搭建高可用nginx集群和Tomcat负载均衡 [购买]
29. 搭建可扩展的ActiveMQ高可用集群 [购买]
30. 实现Mysql数据库的主从复制、读写分离、分表分库、负载均衡和高可用 [购买]
31. 搭建高可用redis集群实现分布式缓存 [购买]
32. [Spring整合Elastic search实现全文检索](https://gitee.com/shenzhanwang/Spring-elastic_search)
#### 特别篇：分布式事务和并发控制
33. 基于可靠消息最终一致性实现分布式事务（activeMQ）[购买]
34. 使用TCC框架实现分布式事务（dubbo版）[购买]
35. 使用TCC框架实现分布式事务（Spring Cloud版）[购买]
36. 决战高并发：数据库锁机制和事务隔离级别的实现 [购买] ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
37. 决战高并发：使用redis实现分布式锁 [购买] ![输入图片说明](https://img.shields.io/badge/-%E7%B2%BE%E5%93%81-orange.svg "在这里输入图片标题")
38. 决战高并发：使用zookeeper实现分布式锁 [购买] 
39. 决战高并发：Java多线程编程实例 [购买]
40. 决战高并发：使用netty实现高性能NIO通信 [购买]

### 可私信或加QQ付费获取源码
![输入图片说明](https://images.gitee.com/uploads/images/2019/0802/083936_7a2d2b52_1110335.png "QQ截图20190802083338.png")