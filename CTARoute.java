package project;

import java.util.ArrayList;

public class CTARoute { 
	private String name;
	private ArrayList<CTAStation> stops ;
	
	public CTARoute(String name){
	       this.stops = new ArrayList<CTAStation>();
	       this.name = name;
		   }
	
	public CTARoute(){
       this.stops = new ArrayList<CTAStation>();	      
	   }
	  
	public void addStation(CTAStation station){
	    this.getStops().add(station);
	   }
	
	public void removeStation(String station){
		
		for( CTAStation stop : stops){
			if(stop.getName().equalsIgnoreCase(station)){
				this.getStops().remove(stop);
				break;
	           	}
	       	}
	   	}
	  
	public void insertStation(int stop,CTAStation station){
		this.getStops().add(stop, station);
	}
	  
	  
	public CTAStation lookupStation(String name){
		CTAStation station =null;
		for( CTAStation stop : stops){
			if(stop.getName().equalsIgnoreCase(name)){
				station = stop;
				break;
			}
		}
      
		return station;
	}
  
	public CTAStation nearestStation(double lat,double lng){
		double minDistance = Double.MAX_VALUE;
		double currentDistance =0;
		int minStopIndex = 0;
		
		for(int i=0; i<stops.size(); i++){
			currentDistance = stops.get(i).calcDistance(lat, lng);
			if (currentDistance < minDistance) {
				minDistance = currentDistance;
				minStopIndex = i;
				currentDistance = 0;
			}
			//System.out.println(minStopIndex);
		}
		return this.stops.get(minStopIndex);
	}

	public CTAStation nearestStation(GeoLocation loc){
		double minDistance =0;
		double currDistance =0;
		int minStopIndex =0;
		for( int i=0;i<stops.size();i++){
			CTAStation currstop = stops.get(i);
			if(i==0){
				minDistance =currstop.calcDistance(loc);
			}
          
			currDistance = currstop.calcDistance(loc);
			if(currDistance > minDistance ){
				minDistance = currDistance;
				minStopIndex =i;
			}
		}
		
		return this.getStops().get(minStopIndex);
	}
  
  
	public String toString(){
		String retString="";
		for( int i=0;i<stops.size();i++){
			CTAStation stop = stops.get(i);
			retString = retString + "Name: "+ stop.getName() + ", Location: " + stop.getLocation() + ", Wheelchair?: " + stop.isWheelchair();
			retString = retString + "\n";
			//System.out.println(retString);
		}     
		return retString;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<CTAStation> getStops() {
		return stops;
	}
	
	public void setStops(ArrayList<CTAStation> stops) {
		this.stops = stops;
	}
	
}

