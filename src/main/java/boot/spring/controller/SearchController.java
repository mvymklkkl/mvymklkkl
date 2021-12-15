package boot.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.spring.elastic.search.service.AggsService;
import boot.spring.elastic.search.service.SearchService;
import boot.spring.pagemodel.DataGrid;
import boot.spring.pagemodel.ElasticSearchRequest;
import boot.spring.pagemodel.QueryCommand;
import boot.spring.pagemodel.RangeQuery;
import boot.spring.pagemodel.ResultData;
import boot.spring.po.Sougoulog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "搜索接口")
@Controller
public class SearchController {

	@Autowired
	SearchService searchService;

	@Autowired
	RestHighLevelClient client;
	
	@Autowired
	AggsService aggsService;
	
	@RequestMapping(value = "/sougoulog", method = RequestMethod.GET)
	public String sougoulog() {
		return "sougoulog";
	}
	
	@ApiOperation("分页查询搜狗日志")
	@RequestMapping(value = "/sougoulog", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid<Object> listsougoulog(@RequestParam(value="current") int current, @RequestParam(value="rowCount") int rowCount
			,@RequestParam(value="searchPhrase") String searchPhrase) {
		DataGrid<Object> grid = new DataGrid<Object>();
		List<Object> data = new ArrayList<>();
		ElasticSearchRequest request = new ElasticSearchRequest();
		QueryCommand query = new QueryCommand();
		query.setIndexname("sougoulog");
		if (StringUtils.isBlank(searchPhrase)) {
			query.setKeyWords("*");
		} else {
			query.setKeyWords(searchPhrase);
		}
		query.setRows(rowCount);
		query.setStart((current-1)*rowCount);
		query.setSort("id");
		request.setQuery(query);
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
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setRows(data);
		grid.setTotal(hits.getTotalHits());
		return grid;
	}	
	

	@ApiOperation("query_string全字段查找")
	@RequestMapping(value = "/query_string", method = RequestMethod.POST)
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

	/**
	 * terms聚集接口
	 * @param content
	 * @return
	 * @throws Exception 
	 */
    @ApiOperation("词条聚集")
	@RequestMapping(value = "/termsAggs", method = RequestMethod.POST)
	@ResponseBody
    public ResultData termsAggs(QueryCommand query) throws Exception{
		ResultData data = aggsService.termsAggs(query);
		return data;
	}
    
	/**
	 * 范围聚集接口
	 * @param content
	 * @return
	 * @throws Exception 
	 */
    @ApiOperation("范围聚集")
	@RequestMapping(value = "/rangeAggs", method = RequestMethod.POST)
	@ResponseBody
    public ResultData rangeAggs(RangeQuery query) throws Exception{
		ResultData data = aggsService.rangeAggs(query);
		return data;
	}    
	
	/**
	 * histogram聚集接口
	 * @param content
	 * @return
	 * @throws Exception 
	 */
    @ApiOperation("直方图聚集")
	@RequestMapping(value = "/histogramAggs", method = RequestMethod.POST)
	@ResponseBody
    public ResultData histogramAggs(QueryCommand query) throws Exception{
		ResultData data = aggsService.histogramAggs(query);
		return data;
	}
	
	/**
	 * datehistogram聚集接口
	 * @param content
	 * @return
	 * @throws Exception 
	 */
    @ApiOperation("日期直方图聚集")
	@RequestMapping(value = "/datehistogramAggs", method = RequestMethod.POST)
	@ResponseBody
    public ResultData datehistogramAggs(QueryCommand query) throws Exception{
		ResultData data = aggsService.datehistogramAggs(query);
		return data;
	}	
}
