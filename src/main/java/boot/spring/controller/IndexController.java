package boot.spring.controller;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.spring.elastic.index.IndexService;
import boot.spring.pagemodel.MSG;
import boot.spring.util.ToolUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "索引接口")
@Controller
public class IndexController {
	@Autowired
	IndexService indexService;
	
	/**
	 * 创建索引并设置字段类型
	 * @param indexname
	 * @param indextype
	 * @param jsonMap
	 * @return
	 * @throws Exception 
	 * rest api:
	    PUT sougoulog
		{
		  "settings": {
		    "analysis": {
		      "filter": {
		        "my_filter": {
		          "type": "stop",
		          "stopwords": ""
		        }
		      },
		      "tokenizer": {
		        "my_tokenizer": {
		          "type": "standard",
		          "max_token_length": "1"
		        }
		      },
		      "analyzer": {
		        "my_analyzer": {
		          "filter": "my_filter",
		          "char_filter": "",
		          "type": "custom",
		          "tokenizer": "my_tokenizer"
		        }
		      }
		    }
		  },
		  "mappings": {
		    "_doc": {
		      "properties": {
		          "clicknum": {
		            "type": "integer"
		          },
		          "keywords": {
		            "type": "text",
		            "analyzer": "my_analyzer"
		          },
		          "rank": {
		            "type": "integer"
		          },
		          "url": {
		            "type": "text",
		            "analyzer": "my_analyzer"
		          },
		          "userid": {
		            "type": "text",
		            "analyzer": "my_analyzer"
		          },
		          "visittime": {
		            "type": "date",
		            "format": "HH:mm:ss"
		          }
		        
		      }
		    }
		  }
		}
	 */
	@ApiOperation("创建各索引并设置字段类型:sougoulog")
	@RequestMapping(value="/createIndexMapping",method = RequestMethod.GET)
	@ResponseBody
	MSG createMapping() throws Exception{
		// 创建sougoulog索引映射
		boolean exsit = indexService.existIndex("sougoulog");
		if ( exsit == false ) {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.startObject("settings");
			  	{
			  		builder.startObject("analysis");
			        {
			        	builder.startObject("filter");
				        {
				        	builder.startObject("my_filter");
					        {
					        	builder.field("type", "stop");
					        	builder.field("stopwords", "");
					        }
					        builder.endObject();
				        }
				        builder.endObject();
				        builder.startObject("tokenizer");
				        {
				        	builder.startObject("my_tokenizer");
					        {
					        	builder.field("type", "standard");
					        	builder.field("max_token_length", "1");
					        }
					        builder.endObject();
				        }
				        builder.endObject();
				        builder.startObject("analyzer");
				        {
				        	builder.startObject("my_analyzer");
					        {
					        	builder.field("filter", "my_filter");
					        	builder.field("char_filter", "");
					        	builder.field("type", "custom");
					        	builder.field("tokenizer", "my_tokenizer");
					        }
					        builder.endObject();
				        }
				        builder.endObject();
			        }
			        builder.endObject();
			  	}
			  	builder.endObject();
			  	
			  	builder.startObject("mappings");
			    {
			    builder.startObject("properties");
			    {
			    	builder.startObject("id");
			        {
			            builder.field("type", "integer");
			        }
			        builder.endObject();
			        builder.startObject("clicknum");
			        {
			            builder.field("type", "integer");
			        }
			        builder.endObject();
			        builder.startObject("keywords");
			        {
			            builder.field("type", "text");
			            builder.field("analyzer", "my_analyzer");
			        }
			        builder.endObject();
			        builder.startObject("rank");
			        {
			            builder.field("type", "integer");
			        }
			        builder.endObject();
			        builder.startObject("url");
			        {
			            builder.field("type", "text");
			            builder.field("analyzer", "my_analyzer");
			        }
			        builder.endObject();
			        builder.startObject("userid");
			        {
			            builder.field("type", "text");
			            builder.field("analyzer", "my_analyzer");
			        }
			        builder.endObject();
			        builder.startObject("visittime");
			        {
			            builder.field("type", "date");
			            builder.field("format", "HH:mm:ss");
			        }
			        builder.endObject();
			    }
			    builder.endObject();
			    }
		        builder.endObject();
			}
			builder.endObject();
			System.out.println(builder.prettyPrint());
			indexService.createMapping("sougoulog",builder);
		}
		return new MSG("index success");
	}
	
	@ApiOperation("向索引sougoulog导入数据")
	@RequestMapping(value="/indexDocs",method = RequestMethod.GET)
	@ResponseBody
	MSG indexDocs() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:SougouQ.log")));
		String s;
		int i = 1;
		List<Map<String, Object>> docs = new ArrayList<>();
		while ((s = br.readLine()) != null) {
			String[] words = s.split(" |\t");
	        System.out.println(words[0]+" "+words[1]+words[2]+words[5]);
	        HashMap<String, Object> doc = new HashMap<String, Object>();
	        doc.put("key", String.valueOf(i));
	        doc.put("id", i);
	        doc.put("visittime", words[0]);
	        doc.put("userid", words[1]);
	        doc.put("keywords", words[2]);
	        doc.put("rank", Integer.parseInt(words[3]));
	        doc.put("clicknum", Integer.parseInt(words[4]));
	        doc.put("url", words[5]);
	        docs.add(doc);
			i++;
		}
		int start = 0;
		while (start < docs.size()) {
			int end = 0;
			if (start + 1000 <= docs.size()) {
				end = start + 1000;
			} else {
				end = docs.size() - 1;
			}
			List<Map<String, Object>> sublist = docs.subList(start, end);
			indexService.indexDocs("sougoulog", "_doc", sublist);
			start += 1000;
		}
		br.close();
		return new MSG("index success");
	}
	
	@ApiOperation("向索引添加或修改一个文档")
	@RequestMapping(value="/indexDoc/{indexname}/{indextype}",method = RequestMethod.POST)
	@ResponseBody
	MSG indexDoc(@PathVariable String indexname, @PathVariable String indextype, @RequestBody Map<String, Object> jsonMap){
		indexService.indexDoc(indexname, indextype, jsonMap);
		return new MSG("index success");
	}	
	
	@ApiOperation("删除一个索引中的文档")
	@RequestMapping(value="/indexDocs/{indexname}/{indextype}/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	MSG indexDocs(@PathVariable String indexname, @PathVariable String indextype, @PathVariable String id){
		int result = indexService.deleteDoc(indexname, indextype, id);
		if ( result < 0 ) {
			return new MSG("index delete failed");
		} else {
			return new MSG("index delete success");
		}
	}	

	
}
