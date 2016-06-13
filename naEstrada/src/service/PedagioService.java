package service;

import java.util.ArrayList;
import java.util.List;

import model.LatLng;
import model.Pedagio;
import util.PolyUtil;
import util.SphericalUtil;

public class PedagioService {

	//private static Map<Long, Pedagio> pedagios;
	private List<Pedagio> pedagios;

	public PedagioService() {
		inicializar();
	}
	
	public List<Pedagio> recuperarPedagios(List<LatLng> polyline) {
		List<Pedagio> retorno = new ArrayList<Pedagio>();
		for (Pedagio pedagio: pedagios) {
			for (int i = 0; i < polyline.size() - 1; i++) {
				LatLng p1 = polyline.get(i);
				LatLng p2 = polyline.get(i + 1);
				LatLng closest = PolyUtil.closestPointOnSegment(pedagio.getLatLng(), p1, p2);
				double distancia = SphericalUtil.computeDistanceBetween(pedagio.getLatLng(), closest);
				if (distancia <= pedagio.getRaio()) {
					retorno.add(pedagio);
					break;
				}
			}
		}
		return retorno;
	}
	
	private void inicializar() {
		pedagios = new ArrayList<Pedagio>();
		
		LatLng latLng = new LatLng(-24.197745, -52.501021);
		pedagios.add(new Pedagio("Viapar", "Campo Mourão", 10.50, latLng, 40, "Campo Mourão - PR"));
		
		latLng = new LatLng(-23.636524, -52.107552);
		pedagios.add(new Pedagio("Viapar", "Floresta", 11.20, latLng, 40, "Floresta - PR"));
		
		latLng = new LatLng(-25.309577, -51.168327);
		pedagios.add(new Pedagio("Caminhos do Paraná", "Relógio", 9.75, latLng, 40, "Prudentópolis - PR"));
	}
	
}