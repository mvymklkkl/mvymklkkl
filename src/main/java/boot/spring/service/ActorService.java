package boot.spring.service;

import java.io.InputStream;
import java.util.List;

import boot.spring.elasticindex.ActorIndex;
import boot.spring.elasticindex.SougoulogIndex;
import boot.spring.po.Actor;


	public interface ActorService {
		List<Actor> getpageActors(int pagenum,int pagesize);
		List<Actor> getActors();
		int getactornum();
		Actor getActorByid(short id);
		Actor updateactor(Actor a);
		Actor addactor(Actor a);
		void delete(short id);
		InputStream getInputStream() throws Exception;
		
		// 批量构建索引
		void indexActors(List<ActorIndex> actors);
		
		void indexSougoulogs(List<SougoulogIndex> logs);
	}
