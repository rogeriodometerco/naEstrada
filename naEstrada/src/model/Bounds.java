package model;

import java.util.ArrayList;
import java.util.List;

import util.SphericalUtil;

public class Bounds {
	private LatLng southWest;
	private LatLng northEast;
	
	public Bounds(LatLng southWest, LatLng northEast) {
		this.southWest = southWest;
		this.northEast = northEast;
	}
	
	public Bounds(double latSouth, double lngWest, double latNorth, double lngEast) {
		this.southWest = new LatLng(latSouth, lngWest);
		this.northEast = new LatLng(latNorth, lngEast);
	}
	
	public Bounds(LatLng centro, double lado) {
		LatLng ponto = new LatLng(centro.getLat(), centro.getLng());
		LatLng norte = SphericalUtil.computeOffset(ponto, lado/2, 0);
		LatLng oeste = SphericalUtil.computeOffset(ponto, lado/2, 90);
		double difLat = norte.getLat() - centro.getLat();
		double difLng = oeste.getLng() - centro.getLng();
		this.southWest = new LatLng(centro.getLat() - difLat, centro.getLng() - difLng);
		this.northEast = new LatLng(centro.getLat() + difLat, centro.getLng() + difLng);
	}
	
	public LatLng getSouthWest() {
		return southWest;
	}

	public void setSouthWest(LatLng southWest) {
		this.southWest = southWest;
	}

	public LatLng getNorthEast() {
		return northEast;
	}

	public void setNorthEast(LatLng northEast) {
		this.northEast = northEast;
	}

	public double getAreaMetros() {
		/*
		return Math.abs(
				(northEast.getLat() - southWest.getLat()) 
				* (northEast.getLat() - southWest.getLat())) 
				* 111119;		
		*/
		List<LatLng> poligono = new ArrayList<LatLng>();
		poligono.add(new LatLng(northEast.getLat(), northEast.getLng()));
		poligono.add(new LatLng(southWest.getLat(), northEast.getLng()));
		poligono.add(new LatLng(southWest.getLat(), southWest.getLng()));
		poligono.add(new LatLng(northEast.getLat(), southWest.getLng()));
		return SphericalUtil.computeArea(poligono);
	}
	/*
	 * Expande a área se necessário para englobar latLng
	 * 
	 */
	public boolean incorporar(LatLng latLng) {
		boolean result = false;
		double latSouthWest = Double.POSITIVE_INFINITY;
		double lngSouthWest = Double.POSITIVE_INFINITY;
		double latNorthEast = Double.NEGATIVE_INFINITY;
		double lngNorthEast = Double.NEGATIVE_INFINITY;

		if (latLng.getLat() < latSouthWest) {
			latSouthWest = latLng.getLat();
			result = true;
		}
		if (latLng.getLng() < lngSouthWest) {
			lngSouthWest = latLng.getLng();
			result = true;
		}
		if (latLng.getLat() > latNorthEast) {
			latNorthEast = latLng.getLat();
			result = true;
		}
		if (latLng.getLng() > lngNorthEast) {
			lngNorthEast = latLng.getLng();
			result = true;
		}
		if (this.southWest.getLat() != latSouthWest | this.southWest.getLng() != lngSouthWest) {
			this.southWest = new LatLng(latSouthWest, lngSouthWest);
		}
		if (this.northEast.getLat() != latNorthEast | this.northEast.getLng() != lngNorthEast) {
			this.northEast = new LatLng(latNorthEast, lngNorthEast);
		}
		return result;
	}
}