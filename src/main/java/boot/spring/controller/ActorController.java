package boot.spring.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.spring.elasticindex.ActorIndex;
import boot.spring.elasticindex.SougoulogIndex;
import boot.spring.mapper.SougoulogMapper;
import boot.spring.pagemodel.ActorGrid;
import boot.spring.pagemodel.MSG;
import boot.spring.po.Actor;
import boot.spring.po.Sougoulog;
import boot.spring.service.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "演员接口")
@Controller
public class ActorController {
	@Autowired
	private ActorService actorservice;
	
	private static final Logger LOG = LoggerFactory.getLogger(ActorController.class);
	
	@ApiOperation("获取所有演员列表")
	@RequestMapping(value="/actors",method = RequestMethod.GET)
	@ResponseBody
	public ActorGrid getactorlist(@RequestParam(value="current") int current,@RequestParam(value="rowCount") int rowCount){
		int total=actorservice.getactornum();
		List<Actor> list=actorservice.getpageActors(current,rowCount);
		ActorGrid grid=new ActorGrid();
		grid.setCurrent(current);
		grid.setRowCount(rowCount);
		grid.setRows(list);
		grid.setTotal(total);
		LOG.debug("获取所有演员列表");
		return grid;
	}
	
	@ApiOperation("修改一个演员")
	@RequestMapping(value="/actors",method = RequestMethod.PUT)
	@ResponseBody
	public Actor updateactor(@RequestBody Actor a){
		Actor actor=actorservice.updateactor(a);
		LOG.debug("修改一个演员");
		return actor;
	}
	
	@ApiOperation("获取一个演员")
	@RequestMapping(value="/actors/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Actor getactorbyid(@PathVariable("id") short id){
		Actor a=actorservice.getActorByid(id);
		LOG.debug("获取一个演员");
		return a;
	}
	
	@ApiOperation("添加一个演员")
	@RequestMapping(value="/actors",method = RequestMethod.POST)
	@ResponseBody
	public Actor add(@RequestBody Actor a){
		Actor actor=actorservice.addactor(a);
		LOG.debug("添加一个演员");
		return actor;
	}
	
	@ApiOperation("删除一个演员")
	@RequestMapping(value="/actors/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") String id){
		actorservice.delete(Short.valueOf(id));
		LOG.debug("删除一个演员");
		return "success";
	}
	
	@ApiOperation("把演员导出为Excel")
	@RequestMapping(value="/exportactor",method = RequestMethod.GET)
	public void export(HttpServletResponse response) throws Exception{
		InputStream is=actorservice.getInputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
		ServletOutputStream output = response.getOutputStream();
		IOUtils.copy(is, output);
	}
	
	@RequestMapping(value="/showactor",method = RequestMethod.GET)
	String showactor(){
		return "showactor";
	}
	
	@ApiOperation("批量添加索引文档")
	@RequestMapping(value="/index/actors",method = RequestMethod.GET)
	@ResponseBody
	public MSG indexActors(){
		List<Actor> list = actorservice.getActors();
		List<ActorIndex> indexes = new ArrayList<ActorIndex>();
		indexes =list.stream().map(a->{
			ActorIndex index = new ActorIndex();
			index.setId(a.getId());
			index.setFirstName(a.getFirst_name());
			index.setLastName(a.getLast_name());
			String dates = a.getLast_update();
			index.setLastUpdate(dates.substring(0, 10));
			return index;
		}).collect(Collectors.toList());
		actorservice.indexActors(indexes);
		return new MSG("index success");
	}	
	
	@Autowired
	SougoulogMapper sougoulogMapper;
	
	@ApiOperation("批量导入日志")
	@RequestMapping(value="/sougoulogs",method = RequestMethod.GET)
	@ResponseBody
	public MSG sougoulogs() throws Exception{
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\dell\\Desktop\\Hadoop-hbase\\SougouQ.log"));
		String s;
		while ((s = br.readLine()) != null) {
			String[] words = s.split(" |\t");
	        System.out.println(words[0]+" "+words[1]+words[2]+words[5]);
	        Sougoulog log = new Sougoulog();
	        log.setVisittime(words[0]);
	        log.setUserid(words[1]);
	        log.setKeywords(words[2]);
	        log.setRank(Integer.parseInt(words[3]));
	        log.setClicknum(Integer.parseInt(words[4]));
	        log.setUrl(words[5]);
	        sougoulogMapper.insert(log);
		}
		return new MSG("import success");
	}		
	
	@ApiOperation("批量添加日志索引文档")
	@RequestMapping(value="/index/sougoulog",method = RequestMethod.GET)
	@ResponseBody
	public MSG indexSougous(){
		List<Sougoulog> list = sougoulogMapper.listSougoulogs();
		List<SougoulogIndex> indexes = new ArrayList<SougoulogIndex>();
		indexes =list.stream().map(a->{
			SougoulogIndex index = new SougoulogIndex();
			index.setId(a.getId());
			index.setVisittime(a.getVisittime());
			index.setUserid(a.getUserid());
			index.setKeywords(a.getKeywords());
			index.setRank(a.getRank());
			index.setClicknum(a.getClicknum());
			index.setUrl(a.getUrl());
			return index;
		}).collect(Collectors.toList());
		actorservice.indexSougoulogs(indexes);
		return new MSG("index success");
	}		
	
	
}
