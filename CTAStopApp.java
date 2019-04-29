package project;

//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
import java.util.*;

import java.io.*;

public class CTAStopApp {
	//To add all the stations
	public static CTARoute alllines = new CTARoute();
	public static CTARoute redline = new CTARoute("Red Line");
	public static CTARoute greenline = new CTARoute("Green Line");
	public static CTARoute blueline = new CTARoute("Blue Line");
	public static CTARoute brownline = new CTARoute("Brown Line");
	public static CTARoute purpleline = new CTARoute("Purple Line");
	public static CTARoute pinkline = new CTARoute("Pink Line");
	public static CTARoute orangeline = new CTARoute("Orange Line");
	public static CTARoute yellowline = new CTARoute("Yellow Line");
	public static ArrayList<CTARoute> lines = new ArrayList<CTARoute>();
	
	public static void main(String[] args) throws IOException, InputMismatchException, Exception{
		lines.add(redline);
		lines.add(greenline);
		lines.add(blueline);
		lines.add(brownline);
		lines.add(purpleline);
		lines.add(pinkline);
		lines.add(orangeline);
		lines.add(yellowline);
		File file = new File ("src/project/CTAStops.csv");
		Scanner scan = new Scanner (file);
		int x = 0;
		while (scan.hasNext()) {
			if (x == 0 || x == 1) {
				scan.nextLine();
				x ++;
				continue;
			}

			String[] split = scan.nextLine().split(",");
			//System.out.println(Arrays.toString(split));
			
			String st = split[0];
			double lat = Double.parseDouble(split[1]);
			double ln = Double.parseDouble(split[2]);
			String location = split[3];
			boolean wheel = split[4].equals("TRUE")? true: false;
			int red = Integer.parseInt(split[5]);
			int green = Integer.parseInt(split[6]);
			int blue = Integer.parseInt(split[7]);
			int brown = Integer.parseInt(split[8]);
			int purple = Integer.parseInt(split[9]);
			int pink = Integer.parseInt(split[10]);
			int orange = Integer.parseInt(split[11]);
			int yellow = Integer.parseInt(split[12]);
			
			CTAStation station = new CTAStation(st, lat, ln, location, wheel, red, green, blue, brown, purple, pink, orange, yellow);
			alllines.getStops().add(station);
			addStationToRoute(red, green, blue, brown, purple, pink, orange, yellow, station);
		}
		
		Scanner sc = new Scanner(System.in);
		boolean done = true;
		while (done){
			System.out.println("Welcome to CTA!");
	        System.out.println("Please select any option ");
	        System.out.println("1. Display the names of all stations");
	        System.out.println("2. Display the stations with wheelchair access");
	        System.out.println("3. Display the nearest station from a location");
	        System.out.println("4. Display information for a station with a specific name");
	        System.out.println("5. Display information for all stations");
	        System.out.println("6. Add a new station");
	        System.out.println("7. Delete an existing station");
	        System.out.println("8. Modify an existing station");
	        System.out.println("9. Generate a path between station");
	        System.out.println("10. Exit");
	        
	        String choice = sc.next();
			switch(choice) {
				case "1":
					System.out.println("1. Display station names: ");
					displayStationNames();
					break;
				
				case "2":
					System.out.println("2. Display station names with/without wheelchair access: press y for yes or press n for no");
					char ch = sc.next().charAt(0);
					displaybyWheelchair(ch);
					break;
					
				case "3":
					System.out.println("Enter the Latitude and Longitude of your location");
					System.out.println("Enter Latitude: ");
					double lat = 0;
					double ln = 0;
					while (!sc.hasNextDouble()) {
						sc.next();
						System.out.println("Please input a number");
					}
					lat = sc.nextDouble();
					System.out.println("Enter Longitude");
					while (!sc.hasNextDouble()) {
						sc.next();
						System.out.println("Please input a number");
					}
					ln = sc.nextDouble();
					System.out.print(alllines.nearestStation(lat, ln));
					break;
						
				case "4":
					System.out.println("Enter Station Name");
					String stationName = sc.next();
					displayStationInfo(stationName);
					break;
					
				case "5":
					allStationsInfo();
					break;
					
				case "6":
					System.out.println("Name:");
		            String name = sc.next();
		            System.out.println("Location:");
		            String loc = sc.next();
		            System.out.println("Latitude:");
		            while (!sc.hasNextDouble()) {
						sc.next();
						System.out.println("Please input an integer");
					}
					double lat1 = sc.nextDouble();
		            System.out.println("Longitude:");
		            while (!sc.hasNextDouble()) {
						sc.next();
						System.out.println("Please input an integer");
					}
					double lng = sc.nextDouble();
		            System.out.println("Wheelchair availability (enter true for yes, false for no)");
		            boolean wheel = sc.nextBoolean();
		            addBeetweenStation(addStation(name, loc, lat1, lng, wheel));
					break;
					
				case "7":
					System.out.println("Name of the station you want to remove");
		            String rem = sc.next();
					alllines.removeStation(rem);
					redline.removeStation(rem);
					greenline.removeStation(rem);
					brownline.removeStation(rem);
					yellowline.removeStation(rem);
					pinkline.removeStation(rem);
					purpleline.removeStation(rem);
					orangeline.removeStation(rem);
					blueline.removeStation(rem);
					break;
					
				case "8":
					System.out.println("Which Station do you want to Modify?");
					String s = sc.next();
					System.out.println("Which route is it in? Red (1), Green (2), Blue(3), Brown(4), Purple(5), Pink(6), Orange(7), Yellow(8)");
					int r = sc.nextInt();
					for (int i = 0; i < lines.get(r-1).getStops().size(); i++) {
						if (s.equalsIgnoreCase(lines.get(r-1).getStops().get(i).getName())){
							System.out.println("What do you want to modify? Geographic Location(1), Location(2), Wheelchair Access(3)");
							int modify = sc.nextInt();
							if (modify == 1) {
								boolean True = true;
								while (True) {
									System.out.println("What is the new Latitude?");
									double newLat = sc.nextDouble();
									System.out.println("What is the new Longitude?");
									double newLng = sc.nextDouble();
									GeoLocation g = new GeoLocation(newLat, newLng);
									if (g.checkLatitute(newLat) == true && g.checkLatitute(newLat) == true) {
										lines.get(r-1).getStops().get(i).setLat(newLat);
										lines.get(r-1).getStops().get(i).setLng(newLng);
										True = false;
									}else {
										System.out.println("You put the wrong input, please enter again.");
									}
								}
							}
							if (modify == 2) {
								System.out.println("What is the new Location?");
								String newLoc = sc.next();
								lines.get(r-1).getStops().get(i).setLocation(newLoc);
							}
							if (modify == 3) {
								System.out.println("We have changed the wheelchair access to " + !lines.get(r-1).getStops().get(i).isWheelchair());
								lines.get(r-1).getStops().get(i).setWheelchair(!lines.get(r-1).getStops().get(i).isWheelchair());
							}
						}else if(i == lines.get(r-1).getStops().size()){
							System.out.println("Station not found");
						}
					}
					break;
				
				case "9":
					System.out.println("From what station?");
					String fStation = sc.next();
					System.out.println("To which station?");
					String sStation = sc.next();
					generatePath(fStation, sStation);
					break;
					
				case "10":
					System.out.print("Goodbye");
					System.out.print("\n");
					done = false;
					break;
					
				default:
					System.out.println("No such thing as " + choice);
					break;
			}
			System.out.println("\n");
		}
	}
			
	public static void displayStationNames() {
		System.out.print("---Red Line---");
		System.out.print("\n");
		for (int x = 0; x < redline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + redline.getStops().get(x).getName());
			}
		System.out.print("\n");
		System.out.print("---Green Line---");
		System.out.print("\n");
		for (int x = 0; x < greenline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + greenline.getStops().get(x).getName());
			}
		System.out.print("\n");
		System.out.print("---Blue Line---");
		System.out.print("\n");
		for (int x = 0; x < blueline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + blueline.getStops().get(x).getName());
			}
		System.out.print("\n");
		
		System.out.print("---Brown Line---");
		System.out.print("\n");
		for (int x = 0; x < brownline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + brownline.getStops().get(x).getName());
			}
		
		System.out.print("\n");
		System.out.print("---Purple Line---");
		System.out.print("\n");
		for (int x = 0; x < purpleline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + purpleline.getStops().get(x).getName());
			}
		System.out.print("\n");
		
		System.out.print("---Pink Line---");
		System.out.print("\n");
		for (int x = 0; x < pinkline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + pinkline.getStops().get(x).getName());
			}
		System.out.print("\n");
		
		System.out.print("---Orange Line---");
		System.out.print("\n");
		for (int x = 0; x < orangeline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + orangeline.getStops().get(x).getName());
			}
		System.out.print("\n");

		System.out.print("---Yellow Line---");
		System.out.print("\n");
		for (int x = 0; x < yellowline.getStops().size(); x++) {
			System.out.println("Station name " + (x + 1) + ". " + yellowline.getStops().get(x).getName());
			}
		}
		
	public static void displaybyWheelchair(char ch) {
		if(ch == 'y' || ch == 'Y'){
            System.out.println("---- Station with Wheel Chair Accessibility ----");
            boolean check = false;
            for (int i=1; i< alllines.getStops().size(); i++){
                if (alllines.getStops().get(i).isWheelchair() == true){
                    System.out.println(alllines.getStops().get(i).getName());
                    check = true;
                }
            }
            
        } else if (ch == 'n' || ch == 'N'){
            System.out.println("---- Station without Wheel Chair Accessibility ----");
            boolean check = false;
            for (int i=0; i< alllines.getStops().size(); i++){
                if (alllines.getStops().get(i).isWheelchair() == false){
                    System.out.println(alllines.getStops().get(i).getName());
                    check =true;
            }
                
        }  if (check == false)
            System.out.println("No such station found");
        } else {
        	System.out.print("Wrong input, please try again");
        }
    }
 	
	public static void displayNearest(double lat, double lng){
		double minDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int minStopIndex = 0;

		for (int i=1; i < alllines.getStops().size(); i++) {
			currentDistance = alllines.getStops().get(i).calcDistance(lat, lng);
			if (currentDistance < minDistance){
				minDistance = currentDistance;
				minStopIndex = i;
				currentDistance = 0;
			}
		}
		System.out.println(alllines.getStops().get(minStopIndex).getName());
	}
	
	public static void displayStationInfo(String stationName){
		boolean exist = false;
		for(CTAStation stop : alllines.getStops()){
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getRed() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Red Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getGreen() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Green Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getBlue() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Blue Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getBrown() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Brown Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getPurple() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Purple Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getPink() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Pink Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getOrange() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Orange Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
	        if(stop.getName().equalsIgnoreCase(stationName) && (stop.getYellow() >= 0)){
	        	System.out.println("Name: " + stop.getName() + " (--Yellow Line--)");
	        	System.out.println("Location: " + stop.getLocation());
	        	System.out.println("Latitude: " + stop.getLat());
	        	System.out.println("Longitude: " + stop.getLng());
	        	System.out.println("Wheelchair access " + stop.isWheelchair());
	        	System.out.println("\n");
	        	exist = true;
	        }
		}
		if (!exist) {
			System.out.println("Station does not exist.");
		}
	}
	
	public static void addStationToRoute(int red, int green, int blue, int brown, int purple, int pink, int orange, int yellow, CTAStation station) {
		if (red >= 0) {
			redline.getStops().add(station);
		}
		if (green >= 0) {
			greenline.getStops().add(station);
		}
		if (blue >= 0) {
			blueline.getStops().add(station);
		}
		if (brown >= 0) {
			brownline.getStops().add(station);
		}
		if (purple >= 0) {
			purpleline.getStops().add(station);
		}
		if (pink >= 0) {
			pinkline.getStops().add(station);
		}
		if (orange >= 0) {
			orangeline.getStops().add(station);
		}
		if (yellow >= 0) {
			yellowline.getStops().add(station);
		}
	}
	
	public static void allStationsInfo() {
		System.out.println("--Red Line--");
		System.out.print(redline.toString());
		System.out.println("\n");
		
		System.out.println("--Green Line--");
		System.out.print(greenline.toString());
		System.out.println("\n");
		
		System.out.println("--Blue Line--");
		System.out.print(blueline.toString());
		System.out.println("\n");
		
		System.out.println("--Brown Line--");
		System.out.print(brownline.toString());
		System.out.println("\n");
		
		System.out.println("--Pink Line--");
		System.out.print(pinkline.toString());
		System.out.println("\n");
		
		System.out.println("--Purple Line--");
		System.out.print(purpleline.toString());
		System.out.println("\n");
		
		System.out.println("--Orange Line--");
		System.out.print(orangeline.toString());
		System.out.println("\n");
		
		System.out.println("--Yellow Line--");
		System.out.print(yellowline.toString());
		System.out.println("\n");
	}
	
	public static CTAStation addStation(String n, String lc, double lat, double lng, boolean wheel) {
		Scanner sc = new Scanner (System.in);
		boolean done = true;
		int red = -1;
		int green = -1;
		int blue = -1;
		int brown = -1;
		int purple = -1;
		int pink = -1;
		int orange = -1;
		int yellow = -1;
		System.out.println("Which route is it in? Red (1), Green (2), Blue(3), Brown(4), Purple(5), Pink(6), Orange(7), Yellow(8)");
		while (done) {
			String route = sc.next();
			switch (route) {
				case "1":
					if (red == 1) {
						System.out.println("You picked red already, pick another one");
						break;
					}else {
						red = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "2":
					if (green == 1) {
						System.out.println("You picked green already, pick another one");
					}else {
						green = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "3":
					if (blue == 1) {
						System.out.println("You picked blue already, pick another one");
					}else {
						blue = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "4":
					if (brown == 1) {
						System.out.println("You picked brown already, pick another one");
					}else {
						brown = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "5":
					if (purple == 1) {
						System.out.println("You picked purple already, pick another one");
					}else {
						purple = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "6":
					if (pink == 1) {
						System.out.println("You picked pink already, pick another one");
					}else {
						pink = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "7":
					if (orange == 1) {
						System.out.println("You picked orange already, pick another one");
					}else {
						orange = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "8":
					if (yellow == 1) {
						System.out.println("You picked yellow already, pick another one");
					}else {
						yellow = 1;
						System.out.println("Any other route? If no, put -1 as the answer");
					}
					break;
				case "-1":
					done = false;
					break;
					
				default:
					System.out.println("No such thing as " + route);
					break;
			}
		}
		CTAStation addStation = new CTAStation(n, lat, lng, lc, wheel, red, green, blue, brown, purple, pink, orange, yellow);
		return addStation;
	}

	public static void addBeetweenStation(CTAStation x) {
		Scanner sc = new Scanner (System.in);
		if (x.getRed() == 1) {
			System.out.println("What is the previous station in red line");
            String prev = sc.next();
            System.out.println("What is the following station in red line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(0).getStops().size(); i++) {
            	if (prev.equals(lines.get(0).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(0).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(0).getStops().add(location, x);
		}
		if (x.getGreen() == 1) {
			System.out.println("What is the previous station in green line");
            String prev = sc.next();
            System.out.println("What is the following station in green line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(1).getStops().size(); i++) {
            	if (prev.equals(lines.get(1).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(1).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(1).getStops().add(location, x);
		}
		if (x.getBlue() == 1) {
			System.out.println("What is the previous station in blue line");
            String prev = sc.next();
            System.out.println("What is the following station in blue line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(2).getStops().size(); i++) {
            	if (prev.equals(lines.get(2).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(2).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(2).getStops().add(location, x);
		}
		if (x.getBrown() == 1) {
			System.out.println("What is the previous station in brown line");
            String prev = sc.next();
            System.out.println("What is the following station in brown line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(3).getStops().size(); i++) {
            	if (prev.equals(lines.get(3).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(3).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(3).getStops().add(location, x);
		}
		if (x.getPurple() == 1) {
			System.out.println("What is the previous station in purple line");
            String prev = sc.next();
            System.out.println("What is the following station in purple line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(4).getStops().size(); i++) {
            	if (prev.equals(lines.get(4).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(4).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(4).getStops().add(location, x);
		}
		if (x.getPink() == 1) {
			System.out.println("What is the previous station in pink line");
            String prev = sc.next();
            System.out.println("What is the following station in pink line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(5).getStops().size(); i++) {
            	if (prev.equals(lines.get(5).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(5).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(5).getStops().add(location, x);
		}
		if (x.getOrange() == 1) {
			System.out.println("What is the previous station in orange line");
            String prev = sc.next();
            System.out.println("What is the following station in orange line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(6).getStops().size(); i++) {
            	if (prev.equals(lines.get(6).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(6).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(6).getStops().add(location, x);
		}
		if (x.getYellow() == 1) {
			System.out.println("What is the previous station in yellow line");
            String prev = sc.next();
            System.out.println("What is the following station in yellow line");
            String fol = sc.next();
            int location = 0;
            for (int i = 0; i < lines.get(7).getStops().size(); i++) {
            	if (prev.equals(lines.get(7).getStops().get(i).getName().toLowerCase()) || (fol.equals(lines.get(7).getStops().get(i).getName().toLowerCase()))) {
        			location = Math.max(location, i);
            	}
            }
            lines.get(7).getStops().add(location, x);
		}
		alllines.getStops().add(x);
		//sc.close();
	}
	
	public static void generatePath(String a, String b) {
		Scanner sc = new Scanner(System.in);
		ArrayList<CTAStation> res = new ArrayList<CTAStation>();
		ArrayList<CTAStation> start = new ArrayList<CTAStation>();
		ArrayList<CTAStation> end = new ArrayList<CTAStation>();
		CTAStation s = null;
		CTAStation e = null;
		ArrayList<int[]> sIndexes= new ArrayList<int[]>();
		ArrayList<int[]> eIndexes= new ArrayList<int[]>();
		int[] sIndex = null; 
		int[] eIndex = null; 
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).getStops().size(); j++) {
				if (a.equalsIgnoreCase(lines.get(i).getStops().get(j).getName())) {
					start.add(lines.get(i).getStops().get(j));
					int[] x = {i, j};
					sIndexes.add(x);
				}
				if (b.equalsIgnoreCase(lines.get(i).getStops().get(j).getName())) {
					end.add(lines.get(i).getStops().get(j));
					int[] y = {i, j};
					eIndexes.add(y);
				}
			}
		}
		
		if (start.size() > 1) {
			System.out.println("Which " + a + "?");
			for(int i = 0; i < start.size(); i++) {
				System.out.println((i+1)+(". ") + "(" + lines.get(sIndexes.get(i)[0]).getName() + ") " + start.get(i).toString());
			}
			int input1 = 0;
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please input an integer");
			}
			input1 = sc.nextInt();
			s = start.get(input1 - 1);
			sIndex = sIndexes.get(input1 - 1);
		}else if (start.size() == 1){
			s = start.get(0);
			sIndex = sIndexes.get(0);
		}else {
			System.out.println("Start Station Not Found.");
		}
		
		if (end.size() > 1) {
			System.out.println("Which " + b + "?");
			for(int i = 0; i < end.size(); i++) {
				System.out.println((i+1)+(". ") + "(" + lines.get(eIndexes.get(i)[0]).getName() + ") " + end.get(i).toString());
			}
			int input2 = 0;
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Please input a integer");
			}
			input2 = sc.nextInt();
			e = end.get(input2 - 1);
			eIndex = eIndexes.get(input2 - 1);
		}else if (end.size() == 1){
			e = end.get(0);
			eIndex = eIndexes.get(0);
		}else {
			System.out.println("End Station Not Found.");
		}
		
		if (sIndex != null && eIndex != null) {
			if (sIndex[0] == eIndex[0]) {
				System.out.println("Start from " + s.getName());
				System.out.println("Drop off at " + e.getName());
				
			}else if (sIndex[0] != eIndex[0]) {
				
				CTAStation transfer = null;
				CTAStation transfer2 = null;
				boolean flag = true;
				boolean flag1 = true;
				boolean flag2 = true;
				
				for (int i = 0; i < lines.get(sIndex[0]).getStops().size();i++) {
					if (flag) {
						for (int j = 0; j < lines.get(eIndex[0]).getStops().size();j++) {
							if (lines.get(sIndex[0]).getStops().get(i).equals(lines.get(eIndex[0]).getStops().get(j))) {
								
								transfer = lines.get(eIndex[0]).getStops().get(j);
								flag = false;
								break;
								
							}
						}
					} else {
						break;
					}
				}
				if (transfer == null) {
					for (int i = 0; i < lines.get(sIndex[0]).getStops().size();i++) {
						if (flag1) {
							for (int j = 0; j < lines.get(0).getStops().size();j++) {
								if (lines.get(sIndex[0]).getStops().get(i).equals(lines.get(0).getStops().get(j))) {
									transfer = lines.get(0).getStops().get(j);
									flag1 = false;
									break;
								}
							}
						}
					}
					for (int i = 0; i < lines.get(eIndex[0]).getStops().size();i++) {
						if (flag2) {
							for (int j = 0; j < lines.get(0).getStops().size();j++) {
								if (lines.get(eIndex[0]).getStops().get(i).equals(lines.get(0).getStops().get(j))) {
									transfer2 = lines.get(0).getStops().get(j);
									flag2 = false;
									break;
								}
							}
						}
					}
					System.out.println("Start from " + s.getName());
					System.out.println("Transfer at " + transfer.getName());
					System.out.println("Transfer again at " + transfer2.getName());
					System.out.println("Drop off at " + e.getName());
				}else {
					System.out.println("Start from " + s.getName());
					System.out.println("Transfer at " + transfer.getName());
					System.out.println("Drop off at " + e.getName());
				}
			}
		}
	}
}