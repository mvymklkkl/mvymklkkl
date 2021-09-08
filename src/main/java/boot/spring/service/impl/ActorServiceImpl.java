package boot.spring.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import boot.spring.elasticindex.ActorIndex;
import boot.spring.elasticindex.SougoulogIndex;
import boot.spring.mapper.ActorMapper;
import boot.spring.po.Actor;
import boot.spring.poi.WriteExcel;
import boot.spring.repository.ActorRepository;
import boot.spring.repository.SougoulogRepository;
import boot.spring.service.ActorService;

import com.github.pagehelper.PageHelper;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service("actorservice")
public class ActorServiceImpl implements ActorService{
	@Autowired
	public ActorMapper actorMapper;
	
	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	SougoulogRepository sougoulogRepository;
	    
	public Actor getActorByid(short id) {
		Actor a=actorMapper.getactorbyid(id);
		return a;
	}

	public Actor updateactor(Actor a) {
		actorMapper.updateActorbyid(a);
		ActorIndex index = new ActorIndex();
		index.setId(a.getId());
		index.setFirstName(a.getFirst_name());
		index.setLastName(a.getLast_name());
		String dates = a.getLast_update();
		index.setLastUpdate(dates.substring(0, 10));
		actorRepository.save(index);
		return a;
	}

	public List<Actor> getpageActors(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);  
		List<Actor> l=actorMapper.getAllactors();
		return l;
	}

	public int getactornum() {
		List<Actor> l=actorMapper.getAllactors();
		return l.size();
	}

	public Actor addactor(Actor a) {
		actorMapper.insertActor(a);
		ActorIndex index = new ActorIndex();
		index.setId(a.getId());
		index.setFirstName(a.getFirst_name());
		index.setLastName(a.getLast_name());
		String dates = a.getLast_update();
		index.setLastUpdate(dates.substring(0, 10));
		actorRepository.save(index);
		return a;
	}

	public void delete(short id) {
		Actor a = actorMapper.getactorbyid(id);
		ActorIndex index = new ActorIndex();
		index.setId(a.getId());
		index.setFirstName(a.getFirst_name());
		index.setLastName(a.getLast_name());
		String dates = a.getLast_update();
		index.setLastUpdate(dates.substring(0, 10));
		actorMapper.delete(id);
		actorRepository.delete(index);
	}

	public InputStream getInputStream() throws Exception {
		String[] title=new String[]{"id","first_name","last_name","last_update"};
		List<Actor> plist=actorMapper.getAllactors();
		List<Object[]>  dataList = new ArrayList<Object[]>();  
		for(int i=0;i<plist.size();i++){
			Object[] obj=new Object[4];
			obj[0]=plist.get(i).getId();
			obj[1]=plist.get(i).getFirst_name();
			obj[2]=plist.get(i).getLast_name();
			obj[3]=plist.get(i).getLast_update();
			dataList.add(obj);
		}
		WriteExcel ex = new WriteExcel(title, dataList);  
		InputStream in;
		in = ex.export();
		return in;
	}

	@Override
	public void indexActors(List<ActorIndex> actors) {
		actorRepository.saveAll(actors);
	}
	@Override
	public List<Actor> getActors() {
		return actorMapper.getAllactors();
	}

	@Override
	public void indexSougoulogs(List<SougoulogIndex> logs) {
		sougoulogRepository.saveAll(logs);
	}
}
