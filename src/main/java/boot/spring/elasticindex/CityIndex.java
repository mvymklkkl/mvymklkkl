package boot.spring.elasticindex;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@Document(indexName = "city",type = "citydocs", shards = 3, replicas = 1)
public class CityIndex {
	@Id
	private short cityid;
	// city是关键词，不会被分词
	@Field(type = FieldType.Keyword)
	private String city;
	// 日期类型
	@Field(type = FieldType.Date, format=DateFormat.date_hour_minute_second)
	private String lastupdate;
	// 构建父子关系
	@Field(type=FieldType.Integer)
	private Short country_id;
	
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
	public Short getCountry_id() {
		return country_id;
	}
	public void setCountry_id(Short country_id) {
		this.country_id = country_id;
	}
}
