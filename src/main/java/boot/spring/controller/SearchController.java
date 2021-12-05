package boot.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import boot.spring.elastic.search.service.SearchService;
import boot.spring.pagemodel.AggsResultResponse;
import boot.spring.pagemodel.ElasticSearchRequest;
import boot.spring.pagemodel.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "搜索接口")
@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	RestHighLevelClient client;
	
	@ApiOperation("query_string全字段查找")
	@RequestMapping(value="/query_string",method = RequestMethod.POST)
	@ResponseBody
	public ResultData query_string(@RequestBody ElasticSearchRequest request) {
			// 搜索结果
			List<Object> data = new ArrayList<Object>();
			SearchResponse searchResponse = searchService.query_string(request);
			SearchHits hits = searchResponse.getHits();
			SearchHit[] searchHits = hits.getHits();
			for (SearchHit hit : searchHits) {
				Map<String, Object> highlights = new HashMap<String, Object>();
				Map<String, Object> map = hit.getSourceAsMap();
				// 获取高亮结果
				Map<String, HighlightField> highlightFields = hit.getHighlightFields();
				for (Map.Entry<String, HighlightField> entry : highlightFields.entrySet()) {
					String mapKey = entry.getKey();
					HighlightField mapValue = entry.getValue();
					Text[] fragments = mapValue.fragments();
					String fragmentString = fragments[0].string();
					highlights.put(mapKey, fragmentString);
				}
				map.put("highlight", highlights);
				data.add(map);
			}
			ResultData resultData = new ResultData();
			resultData.setQtime(new Date());
			resultData.setData(data);
			resultData.setNumberFound(hits.getTotalHits());
			resultData.setStart(request.getQuery().getStart());
			return resultData;
	}

	@ApiOperation("日期直方图聚集")
	@RequestMapping(value="/dateHistogram",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AggsResultResponse> dateHistogram(@RequestBody ElasticSearchRequest request) {
		try {
			HashMap<String, Long> resultMap = searchService.dateHistogram(request);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("aggs", resultMap);
			AggsResultResponse rep = new AggsResultResponse(HttpStatus.OK, 200, "查询成功", "请求成功", map);
			return new ResponseEntity<AggsResultResponse>(rep, HttpStatus.OK);
		} catch (HttpMessageNotReadableException hex) {
			hex.printStackTrace();
			AggsResultResponse rep = new AggsResultResponse(HttpStatus.BAD_REQUEST, 400, "请求格式错误", "请求失败", null);
			return new ResponseEntity<AggsResultResponse>(rep, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			AggsResultResponse rep = new AggsResultResponse(HttpStatus.BAD_REQUEST, 500, "后台错误", "请求失败", null);
			return new ResponseEntity<AggsResultResponse>(rep, HttpStatus.BAD_REQUEST);
		}
	}	

	
}
