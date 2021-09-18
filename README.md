# Spring-elastic_search

### 介绍
Spring boot整合elastic search 6.8.1实现全文检索。主要包含以下特性：

1. 全文检索的实现主要包括构建索引、高级搜索、文本分词三个模块；
2. 索引的构建使用增量更新，当数据库的原始数据发生修改、删除和新增时，把对应的变更在elastic search的索引中要响应地操作一次；
3. 使用spring-boot-starter-data-elasticsearch来操作elasticsearch，构建索引时，根据实际情况考虑哪些字段需要分词，哪些不需要分词，这会影响搜索结果
4. 高级搜索实现了以下几种：
    - 普通查询,先分词再精准搜索：matchQuery
    - 精准查询,不分词直接搜索：termQuery
    - 模糊查询,搜索字符串接近的词：fuzzyQuery
    - 布尔查询,使用布尔运算组合多个查询条件：boolQuery
    - 范围查询：rangeQuery
    - 前缀查询：prefixQuery
    - 通配符查询：wildcardQuery
5. 文本分词使用了IK分词器：https://github.com/medcl/elasticsearch-analysis-ik
6. 原始数据提交到倒排索引中以前，es可以对原始数据进行一系列的转换操作，这个过程叫做分析。一个完整的分析过程，要经过大于等于0个字符过滤器，一个分词器，大于等于0个分词过滤器组成。
7. swagger入口：http://localhost:8080/swagger-ui.html
### 效果图
![输入图片说明](https://images.gitee.com/uploads/images/2019/0802/161859_ea43de26_1110335.png "QQ截图20190802161813.png")