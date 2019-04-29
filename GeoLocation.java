package project; 

public class GeoLocation {
//Create 2 instance variable
	private double lat;
	private double lng;
	
//create default constructor
	public GeoLocation() {
		
	}
	
//create non-default constructor
	public GeoLocation(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
//Write 2 accessor methods, one for each instance variable.
	public double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
//Write 2 mutator methods, one for each instance variable.
	public void setLat(double la) {
		if (checkLatitute(la)) {
		lat = la;
		}
	}
	
	public void setLng(double ln) {
		if (checkLongitute(ln))
		lng = ln;
	}

//Write a method that will return the location in the format "(lat, lng)" (the toString method)
	public String toString() {
		String format = "(" + this.lat + "," + this.lng + ")";
		return format;
	}
	
//Write a method that will return true if the latitude is between -90 and +90.
	public boolean checkLatitute(double la) {
		if (la >= -90 && la<=90) {
			return true;
		}
			return false;
	}

//Write a method that will return true if the longitude is between -180 and +180.
	public boolean checkLongitute(double ln) {
		if (ln >= -180 && ln<=180) {
			return true;
		}
			return false;
	}
}
