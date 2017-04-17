package Lab6;

public class GeoLocation {
	
	/*This program allow users to look up stations by name, add new stations, and delete existing stations.*/
	
	//Declare instance variables.
	protected Double latitude;
	protected Double longitude;

	public GeoLocation() {	//Default constructor.
		latitude = 0.0;
		longitude = 0.0;	
	}
	
	public GeoLocation(double lat, double lon) {  //Nondefault constructor.
		latitude = lat;
		longitude = lon;	
	}
	
	//Mutator methods
	public void setLatitude(double lat) {
		latitude = lat;
	}
	
	public void setLongitude(double lon) {
		longitude = lon;
	}
	
	//Accessor methods
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String toString() {  //Method that returns the lat and lon in a sentence.
		return "(" + latitude + ", " + longitude + ")";
	}
	
	public boolean equals(GeoLocation a) {  //Method sees whether two variables are the same
		return (Math.abs(latitude - a.getLatitude())<0.001) && (Math.abs(longitude - a.getLongitude())<0.001);
	}
	
	public double calcDistance(GeoLocation other) {  //Method for calculating distance between coordinates using haversine formula
		double lat1 = Math.toRadians(latitude);
		double lon1 = Math.toRadians(longitude);
		double lat2 = Math.toRadians(other.latitude);
		double lon2 = Math.toRadians(other.longitude);
		double deltalat = Math.toRadians(lat2-lat1);
		double deltalon = Math.toRadians(lon2-lon1);
		double r = 3959.0;
		double a = Math.pow(Math.sin((deltalat)/2), 2) * 
				Math.cos(lat1) * Math.cos(lat2) * 
				Math.pow(Math.sin((deltalon)/2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = r * c;
		return d;
	}
	
	
	public double calcDistance(double lat, double lon) {  //Method for calculating distance between coordinates using haversine formula
		double lat1 = Math.toRadians(latitude);
		double lon1 = Math.toRadians(longitude);
		double lat2 = lat;
		double lon2 = lon;
		double deltalat = Math.toRadians(lat2-lat1);
		double deltalon = Math.toRadians(lon2-lon1);
		double r = 3959.0;
		double a = Math.pow(Math.sin((deltalat)/2), 2) * 
				Math.cos(lat1) * Math.cos(lat2) * 
				Math.pow(Math.sin((deltalon)/2), 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = r * c;	
		return d;
	}
	
	
}
