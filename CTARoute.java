package Lab6;

import java.util.ArrayList;
	
public class CTARoute {
	
	private String name;
	private ArrayList<CTAStation> stops;
	
	public CTARoute() {
		name = "Unknown";
		stops = new ArrayList<CTAStation>();
	}
	
    public CTARoute(String name) {
        this.name = name;
        stops = new ArrayList<CTAStation>();
    }
	
	public String getName() {
		return name;
	}
	
	public ArrayList<CTAStation> getStops() {
		return stops;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setStops(ArrayList<CTAStation> stops) {
		this.stops = stops;
	}
	
    public boolean equals(CTARoute r) {
        if (!name.equals(r.getName())) {
            return false;
        }
         
        ArrayList<CTAStation> other = r.getStops();
        if (stops.size() != other.size()) {
            return false;
        }
         
        for (int i=0; i<stops.size(); i++) {
            if (!stops.get(i).equals(other.get(i))) {
                return false;
            }
        }
         
        return true;
    }
	
    public String toString() {
        String result = name;
         
        for (CTAStation station : stops) {
            result = result + "\n" + station.getName();
        }
         
        return result;
    }
	
	public void addStation(CTAStation cta) {
		stops.add(cta);
	}
	
    public void removeStation(CTAStation station) {
        for (CTAStation s : stops) {
            if (s.equals(station)) {
                stops.remove(s);
                return;
            }
        }
    }
	
	public void insertStation(int index, CTAStation cta) {
		stops.add(index, cta);
	}
	
    public CTAStation lookupStation(String name) {
        for (CTAStation s : stops) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
         
        return null;
    }
    
    public int findStation(String name) {
        for (int i=0; i<stops.size(); i++) {
            if (stops.get(i).getName().equals(name)) {
                return i;
            }
        }
         
        return -1;
    }
	
    public CTAStation nearestStation(double lat, double lng) {
        CTAStation closest = stops.get(0);
        double cDistance = closest.calcDistance(lat, lng);
         
        for (CTAStation s : stops) {
            double nDistance = s.calcDistance(lat, lng);
            if (nDistance < cDistance){
                closest = s;
            }
        }
         
        return closest;
    }
	
    public CTAStation nearestStation(GeoLocation g) {
        CTAStation closest = stops.get(0);
        double cDistance = closest.calcDistance(g);
         
        for (CTAStation s : stops) {
            double nDistance = s.calcDistance(g);
            if (nDistance < cDistance){
                closest = s;
            }
        }
         
        return closest;
    }
}
