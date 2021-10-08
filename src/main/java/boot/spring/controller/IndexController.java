package boot.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import boot.spring.elasticindex.ActorIndex;
import boot.spring.elasticindex.ShopIndex;
import boot.spring.elasticindex.SougoulogIndex;
import boot.spring.mapper.SougoulogMapper;
import boot.spring.pagemodel.MSG;
import boot.spring.po.Actor;
import boot.spring.po.Sougoulog;
import boot.spring.repository.ActorRepository;
import boot.spring.repository.ShopRepository;
import boot.spring.repository.SougoulogRepository;
import boot.spring.service.ActorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "索引的接口")
@Controller
public class IndexController {
	
	@Autowired
	SougoulogRepository sougoulogRepository;
	
	@Autowired
	SougoulogMapper sougoulogMapper;
	
	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	ActorService actorservice;
	
	@Autowired
	ShopRepository shopRepository;
	
	@ApiOperation("全量添加日志表索引文档")
	@RequestMapping(value="/index/sougoulog/full",method = RequestMethod.GET)
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
		sougoulogRepository.saveAll(indexes);
		return new MSG("index success");
	}	
	
	@ApiOperation("增量添加一个日志表索引文档")
	@RequestMapping(value="/index/sougoulog",method = RequestMethod.POST)
	@ResponseBody
	public MSG indexSougoulog(@RequestBody SougoulogIndex index){
		sougoulogRepository.save(index);
		return new MSG("index success");
	}
	
	@ApiOperation("增量添加一组日志表索引文档")
	@RequestMapping(value="/index/sougoulogs",method = RequestMethod.POST)
	@ResponseBody
	public MSG indexSougoulogs(@RequestBody List<SougoulogIndex> index){
		sougoulogRepository.saveAll(index);
		return new MSG("index success");
	}	
	
	@ApiOperation("删除一个日志表索引文档")
	@RequestMapping(value="/index/sougoulogs/{id}",method = RequestMethod.DELETE)
	@ResponseBody
	public MSG indexSougoulogs(@PathVariable Integer id){
		Optional<SougoulogIndex>  index = sougoulogRepository.findById(id);
		if (index.isPresent()) {
			sougoulogRepository.delete(index.get());
			return new MSG("delete success");
		} else {
			return new MSG("delete fail");
		}
	}	
	
	@ApiOperation("全量添加演员表索引文档")
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
		actorRepository.saveAll(indexes);
		return new MSG("index success");
	}		
	
	@ApiOperation("增量添加一组坐标数据")
	@RequestMapping(value="/index/shops",method = RequestMethod.GET)
	@ResponseBody
	public MSG indexShops(){
		List<ShopIndex> indexes = new ArrayList<ShopIndex>();
		ShopIndex a = new ShopIndex();
		a.setId(1);
		a.setName("北京");
		a.setLocation(new GeoPoint(39.9047253699, 116.4072154982));
		indexes.add(a);
		ShopIndex b = new ShopIndex();
		b.setId(2);
		b.setName("上海");
		b.setLocation(new GeoPoint(31.2304324029, 121.4737919321));
		indexes.add(b);
		ShopIndex c = new ShopIndex();
		c.setId(3);
		c.setName("天津");
		c.setLocation(new GeoPoint(39.0850853357, 117.1993482089));
		indexes.add(c);
		ShopIndex d = new ShopIndex();
		d.setId(4);
		d.setName("顺义");
		d.setLocation(new GeoPoint(40.1299127031, 116.6569478577));
		indexes.add(d);
		shopRepository.saveAll(indexes);
		return new MSG("index success");
	}	
	
}
