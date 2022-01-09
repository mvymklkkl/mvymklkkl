package boot.spring.pagemodel;

public class GeoDistance {
	// 经度
	Double longitude;
	
	// 纬度
	Double latitude;
	
	// 检索半径
	Double distance;

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
}
