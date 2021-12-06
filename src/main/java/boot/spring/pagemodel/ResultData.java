package boot.spring.pagemodel;

import java.util.Date;
import java.util.List;

public class ResultData {
	Long NumberFound;
	
	Integer start;
	
	Date qtime;
	
	List<Object> data;

	public Long getNumberFound() {
		return NumberFound;
	}

	public void setNumberFound(Long numberFound) {
		NumberFound = numberFound;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public Date getQtime() {
		return qtime;
	}

	public void setQtime(Date qtime) {
		this.qtime = qtime;
	}
	
}
