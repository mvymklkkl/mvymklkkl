package boot.spring.elastic.index;

import java.util.List;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;

public interface IndexService {
	/**
	 * 创建索引映射
	 * @param indexname
	 * @param mapping
	 */
	void createMapping(String indexname, XContentBuilder mapping);
	/**
	 * 索引一篇文档
	 * @param indexName
	 * @param indexType
	 * @param doc
	 */
	void indexDoc(String indexName, String indexType, Map<String, Object> doc);
	/**
	 * 索引一组文档
	 * @param indexName
	 * @param indexType
	 * @param docs
	 */
	void indexDocs(String indexName, String indexType, List<Map<String, Object>> docs);
	
	/**
	 * 删除一篇文档
	 * @param indexName
	 * @param indexType
	 * @param id
	 * @return
	 */
	int deleteDoc(String indexName, String indexType, String id);
	/**
	 * 判断一个索引是否存在
	 * @param indexname
	 * @return
	 */
	boolean existIndex(String indexname);
}
