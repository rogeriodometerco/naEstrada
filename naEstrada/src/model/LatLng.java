package model;

import javax.persistence.Embeddable;

@Embeddable
public class LatLng {
	private double lat;
	private double lng;

	public LatLng() {
		lat = 0;
		lng = 0;
	}
	
	public LatLng(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLng() {
		return lng;
	}
	
	public boolean equals(LatLng outra) {
		return lat == outra.getLat() && lng == outra.getLng();
	}
	
	public String toString() {
		return lat + ", " + lng;
	}
}
