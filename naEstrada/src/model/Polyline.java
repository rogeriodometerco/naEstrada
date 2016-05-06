package model;

import java.util.ArrayList;
import java.util.List;

public class Polyline {
	private List<LatLng> path;
	private String encodedPath;
	private Bounds bounds;

	public Polyline(List<LatLng> path) {
		this.path = path;
	}

	public Polyline(String encodedPath) {
		this.encodedPath = encodedPath;
	}

	public List<LatLng> getPath() {
		if (this.path == null) {
			this.path = decode(encodedPath);
		}
		return path;
	}

	public List<LatLng> get(int inicio, int elementos) {
		/*
		List<LatLng> retorno = new ArrayList<LatLng>();
		for (int i = inicio; i < elementos && i < getPath().size(); i++) {
			retorno.add(getPath().get(i));
		}
		*/
		List<LatLng> retorno = new ArrayList<LatLng>();
		int j = inicio + elementos;
		for (int i = inicio; i < j && i < getPath().size(); i++) {
			retorno.add(getPath().get(i));
		}

		return retorno;
	}

	public String getEncodedPath() {
		if (this.encodedPath == null || this.encodedPath.equals("")) {
			this.encodedPath = encode(path);
		} 
		return encodedPath;
	}	

	public void adicionarLatLng(LatLng latLng) {
		this.getPath().add(latLng);
		this.encodedPath = null;
		this.bounds.incorporar(latLng);
	}

	public Bounds getBounds() {
		if (bounds == null && this.getPath() != null && this.getPath().size() > 0) {
			double minLat = Double.POSITIVE_INFINITY;
			double minLng = Double.POSITIVE_INFINITY;
			double maxLat = Double.NEGATIVE_INFINITY;
			double maxLng = Double.NEGATIVE_INFINITY;

			for (LatLng latLng: getPath()) {

				if (latLng.getLat() < minLat) {
					minLat = latLng.getLat();
				}
				if (latLng.getLng() < minLng) {
					minLng = latLng.getLng();
				}
				if (latLng.getLat() > maxLat) {
					maxLat = latLng.getLat();
				}
				if (latLng.getLng() > maxLng) {
					maxLng = latLng.getLng();
				}
			}
			bounds = new Bounds(minLat, minLng, maxLat, maxLng);
		}
		return bounds;
	}

	private List<LatLng> decode(String encodedPath) {
		int len = encodedPath.length();
		final List<LatLng> path = new ArrayList<LatLng>(len / 2);
		int index = 0;
		int lat = 0;
		int lng = 0;

		while (index < len) {
			int result = 1;
			int shift = 0;
			int b;
			do {
				b = encodedPath.charAt(index++) - 63 - 1;
				result += b << shift;
				shift += 5;
			} while (b >= 0x1f);
			lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);
			result = 1;
			shift = 0;
			do {
				b = encodedPath.charAt(index++) - 63 - 1;
				result += b << shift;
				shift += 5;
			} while (b >= 0x1f);
			lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);
			path.add(new LatLng(((double)lat * 1e-5), ((double)lng * 1e-5) ));
		}
		return path;
	}

	/**
	 * Encodes a sequence of LatLngs into an encoded path string.
	 */
	private String encode(List<LatLng> path) {
		long lastLat = 0;
		long lastLng = 0;

		StringBuffer result = new StringBuffer();

		for (LatLng point : path) {
			long lat = Math.round(point.getLat() * 1e5);
			long lng = Math.round(point.getLng() * 1e5);

			long dLat = lat - lastLat;
			long dLng = lng - lastLng;

			encode(dLat, result);
			encode(dLng, result);

			lastLat = lat;
			lastLng = lng;
		}
		return result.toString();
	}

	private void encode(long v, StringBuffer result) {
		v = v < 0 ? ~(v << 1) : v << 1;
		while (v >= 0x20) {
			result.append(Character.toChars((int) ((0x20 | (v & 0x1f)) + 63)));
			v >>= 5;
		}
		result.append(Character.toChars((int) (v + 63)));
	}

}