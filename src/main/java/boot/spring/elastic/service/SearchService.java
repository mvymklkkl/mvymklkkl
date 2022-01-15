package boot.spring.elastic.service;

import java.util.HashMap;

import org.elasticsearch.action.search.SearchResponse;

import boot.spring.pagemodel.ElasticSearchRequest;
import boot.spring.pagemodel.GeoDistance;

public interface SearchService {
	/**
	 * 多字段搜索
	 * @param request
	 * @return
	 */
	SearchResponse query_string(ElasticSearchRequest request);
	
	/**
	 * 精准搜索
	 */
	SearchResponse termSearch(String index, String field,String term);
	
	/**
	 * 搜索全部
	 */
	SearchResponse matchAllSearch(String index);
	
	/**
	 * 经纬度搜索
	 */
	SearchResponse geoDistanceSearch(String index, GeoDistance geo, Integer pagenum, Integer pagesize);
}
