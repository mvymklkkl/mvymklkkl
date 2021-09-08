package boot.spring.controller;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.spring.elasticindex.ActorIndex;
import boot.spring.elasticindex.SougoulogIndex;
import boot.spring.pagemodel.ActorGrid;
import boot.spring.po.Actor;
import boot.spring.repository.ActorRepository;
import boot.spring.repository.SougoulogRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "搜索接口")
@Controller
public class SearchController {
	
	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	SougoulogRepository sougoulogRepository;
	
	@ApiOperation("普通查询,先分词再精准搜索")
	@RequestMapping(value="/matchSearch",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> matchSearch(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加基本分词查询
	    queryBuilder.withQuery(QueryBuilders.matchQuery("keywords", "国际国产手机"));
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
	    // 总条数
//	    long total = result.getTotalElements();
		return result;
	}
	
	@ApiOperation("精准查询,不分词直接搜索")
	@RequestMapping(value="/termSearch",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> termSearch(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加精准搜索条件
	    queryBuilder.withQuery(QueryBuilders.termQuery("visittime", "00:00:00"));
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
		return result;
	}	
	
	@ApiOperation("模糊查询,搜索字符串接近的词")
	@RequestMapping(value="/fuzzyQuery",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> fuzzyQuery(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加模糊搜索条件
	    queryBuilder.withQuery(QueryBuilders.fuzzyQuery("visittime", "0:00:1"));
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
		return result;
	}
	
	@ApiOperation("布尔查询,使用布尔运算组合多个查询条件")
	@RequestMapping(value="/boolQuery",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> boolQuery(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加布尔搜索条件
	    queryBuilder.withQuery(QueryBuilders.boolQuery()
	    		.must(QueryBuilders.matchQuery("visittime","00:00:11"))// 必须有
                .should(QueryBuilders.termQuery("keywords","世界"))// 可以有
                .mustNot(QueryBuilders.termQuery("userid","336233819810687")));// 不能有
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
		return result;
	}	
	
	@ApiOperation("范围查询")
	@RequestMapping(value="/rangeQuery",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> rangeQuery(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加范围搜索条件
	    queryBuilder.withQuery(QueryBuilders.rangeQuery("visittime").from("00:00:11").to("00:01:11"));
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
		return result;
	}		
	
	@ApiOperation("前缀查询")
	@RequestMapping(value="/prefixQuery",method = RequestMethod.GET)
	@ResponseBody
	public Page<SougoulogIndex> prefixQuery(){
		Pageable pageable = PageRequest.of(0, 1000);
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加前缀查询条件
	    queryBuilder.withQuery(QueryBuilders.prefixQuery("visittime", "00:01"))
	    			.withSort(SortBuilders.fieldSort("visittime").order(SortOrder.DESC))//排序
	    			.withPageable(pageable);//分页
	    // 搜索，获取结果
	    Page<SougoulogIndex> result = sougoulogRepository.search(queryBuilder.build());
		return result;
	}	
	
	@ApiOperation("通配符查询")
	@RequestMapping(value="/wildcardQuery",method = RequestMethod.GET)
	@ResponseBody
	public Page<ActorIndex> wildcardQuery(){
		 // 构建查询条件
	    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
	    // 添加前缀查询条件
	    queryBuilder.withQuery(QueryBuilders.wildcardQuery("firstName", "B*"));
	    			
	    // 搜索，获取结果
	    Page<ActorIndex> result = actorRepository.search(queryBuilder.build());
		return result;
	}		
}
