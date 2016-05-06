package model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DirectionsResult {
	private Polyline polyline;
	
	private DirectionsResult() {
	}
	
	public DirectionsResult(String directionsResponse) throws Exception {
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(directionsResponse);
		JsonArray routes = json.getAsJsonObject().get("routes").getAsJsonArray();
		List<LatLng> path = new ArrayList<LatLng>();
		
		for (int i = 0; i < routes.size(); i++) {
			JsonObject route = routes.get(i).getAsJsonObject();
			JsonArray legs = route.get("legs").getAsJsonArray();
			
			for (int j = 0; j < legs.size(); j++) {
				JsonObject leg = legs.get(j).getAsJsonObject();
				JsonArray steps = leg.get("steps").getAsJsonArray();
				
				for (int k = 0; k < steps.size(); k++) {
					JsonObject step = steps.get(k).getAsJsonObject();
					JsonObject polyline = step.get("polyline").getAsJsonObject();
					String points = polyline.get("points").getAsString();
					System.out.print("DirectionsResult - points: " + points);
					List<LatLng> lista = new Polyline(points).getPath();
					
					// Descarta primeiro ponto se o último da iteração anterior for igual.
					if (path.size() > 0 && lista.get(0).equals(path.get(path.size()-1))) {
						//lista.remove(0);
					}

					path.addAll(lista);
				}
			}			
		}		
		this.polyline = new Polyline(path);
	}
	
	public Polyline getPolyline() {
		return polyline;
	}
}