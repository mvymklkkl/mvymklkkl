package boot.spring.elastic.search.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.ConstantScoreQuery;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import boot.spring.elastic.search.querytypes.FuzzyQuery;
import boot.spring.elastic.search.querytypes.MatchQuery;
import boot.spring.elastic.search.querytypes.RangeQuery;
import boot.spring.elastic.search.querytypes.TermQuery;
import boot.spring.elastic.search.service.SearchService;
import boot.spring.pagemodel.ElasticSearchRequest;
import boot.spring.pagemodel.FilterCommand;
import boot.spring.util.ToolUtils;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	RestHighLevelClient client;
	
	@Override
	public SearchResponse query_string(ElasticSearchRequest request) {
		SearchRequest searchRequest = new SearchRequest(request.getQuery().getIndexname());
		// ???????????????????????????????????????
		String content = request.getQuery().getKeyWords();
		Integer rows = request.getQuery().getRows();
		if (rows == null || rows == 0) {
			rows = 10;
		}
		Integer start = request.getQuery().getStart();
		if (content == null || "".equals(content)) {
			// ????????????
			content = "*";
		}
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		// ??????????????????
		BoolQueryBuilder builder;
        if("*".equalsIgnoreCase(content)){
            builder = QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(content).defaultOperator(Operator.AND));
        }else {
            builder = QueryBuilders.boolQuery().must(QueryBuilders.queryStringQuery(ToolUtils.handKeyword(content)).defaultOperator(Operator.AND));
        }
		// ??????????????????
		FilterCommand filter = request.getFilter();
		if (filter != null) {
			List<RangeQuery> ranges = filter.getRanges();
			if (ranges != null && ranges.size() > 0) {
				for (RangeQuery range : ranges) {
					builder.must(QueryBuilders.rangeQuery(range.getField()).from(range.getFrom()).to(range.getTo()));
				}
			}
			List<TermQuery> terms = filter.getTerms();
			if (terms != null && terms.size() > 0) {
				for (TermQuery term : terms) {
					builder.must(QueryBuilders.termQuery(term.getField(), term.getValue()));
				}
			}
	
			List<FuzzyQuery> fuzzys = filter.getFuzzys();
			if (fuzzys != null && fuzzys.size() > 0) {
				for (FuzzyQuery fuzzy : fuzzys) {
					builder.must(QueryBuilders.fuzzyQuery(fuzzy.getField(), fuzzy.getValue()));
				}
			}
			
			List<MatchQuery> matches = filter.getMatches();
			if (matches != null && matches.size() > 0) {
				for (MatchQuery match : matches) {
					builder.must(QueryBuilders.matchQuery(match.getField(), match.getValue()));
				}
			}		
		}
		// ??????
		if(StringUtils.isNoneBlank(request.getQuery().getSort())){
			searchSourceBuilder.sort(request.getQuery().getSort(), SortOrder.ASC);
	    }
	    searchSourceBuilder.query(builder);
	    // ????????????
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("*");
        searchSourceBuilder.highlighter(highlightBuilder);
		searchSourceBuilder.from(start);
		searchSourceBuilder.size(rows);
		
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchResponse;
	}


	@Override
	public SearchResponse termSearch(String index, String field, String term) {
		SearchRequest searchRequest = new SearchRequest(index);
		BoolQueryBuilder builder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery(field, term));
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(builder);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			 searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return searchResponse;
	}
	
}
