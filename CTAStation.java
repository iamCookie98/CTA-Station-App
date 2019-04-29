package project;
//Station class
 
public class CTAStation extends GeoLocation{
	private String name;
	private String location;
	private boolean wheelchair;
	private int red;
	private int green;
	private int blue;
	private int brown;
	private int purple;
	private int pink;
	private int orange;
	private int yellow;
	
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getBrown() {
		return brown;
	}

	public void setBrown(int brown) {
		this.brown = brown;
	}

	public int getPurple() {
		return purple;
	}

	public void setPurple(int purple) {
		this.purple = purple;
	}

	public int getPink() {
		return pink;
	}

	public void setPink(int pink) {
		this.pink = pink;
	}

	public int getOrange() {
		return orange;
	}

	public void setOrange(int orange) {
		this.orange = orange;
	}

	public int getYellow() {
		return yellow;
	}

	public void setYellow(int yellow) {
		this.yellow = yellow;
	}

	public CTAStation() {
		
	}
	
	public CTAStation(String name, double lat, double lng, String location, boolean wheelchair, int red, int green, int blue, int brown, int purple, int pink, int orange, int yellow) {
		super(lat, lng);
		this.name = name;
		this.location = location;
		this.wheelchair = wheelchair;
		this.red = red;
		this.blue = blue;
		this.brown = brown;
		this.green = green;
		this.orange = orange;
		this.pink = pink;
		this.purple = purple;
		this.yellow = yellow;
	}

	public double calcDistance(double lat2, double lng2){
		return Math.sqrt(Math.pow(getLat()-lat2,2)+Math.pow(getLng()-lng2,2));
	}
	
	public double calcDistance(GeoLocation loc2){
		return Math.sqrt(Math.pow(getLat()-loc2.getLat(),2)+Math.pow(getLng()-loc2.getLng(),2));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isWheelchair() {
		return wheelchair;
	}

	public void setWheelchair(boolean wheelchair) {
		this.wheelchair = wheelchair;
	}
	
	public boolean equals(CTAStation a) {
		
		if (!this.name.equals(a.getName())) {
			
			return false;
			
		}
		if (!this.location.equals(a.getLocation())) {
			
			return false;
			
		}
		if (this.wheelchair != a.isWheelchair()) {
	
			return false;
	
		}
		if (this.getLat() != a.getLat()) {
			
			return false;
			
		}
		if (this.getLng() != a.getLng()) {
			
			return false;
			
		}
		return true;
	}
	
	public String toString() {
		/*
		if (this.getRed() >= 0) {
			System.out.println("(--- Red Line---)");
		}
		if (this.getGreen() >= 0) {
			System.out.println("(--- Green Line---)");
		}
		if (this.getBlue() >= 0) {
			System.out.println("(--- Blue Line---)");
		}
		if (this.getBrown() >= 0) {
			System.out.println("(--- Brown Line---)");
		}
		if (this.getPurple() >= 0) {
			System.out.println("(--- Purple Line---)");
		}
		if (this.getPink() >= 0) {
			System.out.println("(--- Pink Line---)");
		}
		if (this.getOrange() >= 0) {
			System.out.println("(--- Orange Line---)");
		}
		if (this.getYellow() >= 0) {
			System.out.println("(--- Yellow Line---)");
		}*/
		return "Station: " + this.getName() + ", Latitude: " + this.getLat() + ", Longitude: " + this.getLng() + ", Location: " + this.getLocation() + ", Wheelchair?: " + this.isWheelchair();
	}
	
}
