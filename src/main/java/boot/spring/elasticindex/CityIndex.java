package boot.spring.elasticindex;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "city",type = "docs", shards = 1, replicas = 0)
public class CityIndex {
	@Id
	private short cityid;
	// city是关键词，不会被分词
	@Field(type = FieldType.Keyword)
	private String city;
	// 日期类型
	@Field(type = FieldType.Date)
	private String lastupdate;
	public short getCityid() {
		return cityid;
	}
	public void setCityid(short cityid) {
		this.cityid = cityid;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLastupdate() {
		return lastupdate;
	}
	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}
	
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		List<Integer> tmp = list.subList(1, 3);
		list.stream().forEach(a->System.out.print(a));
//		tmp.stream().forEach(a->System.out.println(a));
	}

}
