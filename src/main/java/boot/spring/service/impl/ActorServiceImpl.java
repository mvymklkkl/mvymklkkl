package boot.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import boot.spring.mapper.ActorMapper;
import boot.spring.po.Actor;
import boot.spring.service.ActorService;

import com.github.pagehelper.PageHelper;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
@Service("actorservice")
public class ActorServiceImpl implements ActorService{
	@Autowired
	public ActorMapper actorMapper;
	
	public Actor getActorByid(short id) {
		Actor a=actorMapper.getactorbyid(id);
		return a;
	}

	public Actor updateactor(Actor a) {
		actorMapper.updateActorbyid(a);
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
		return a;
	}

	public void delete(short id) {
		Actor a = actorMapper.getactorbyid(id);
		actorMapper.delete(id);
	}


	@Override
	public List<Actor> getActors() {
		return actorMapper.getAllactors();
	}

}
