package resource;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.google.gson.GsonBuilder;

import model.Pedagio;
import service.PedagioService;
import util.PolyUtil;

@Path("/viagem")
public class PedagioResource {

	@GET
	public String ola() {
		return "ola";
	}

	private PedagioService pedagioService;
	
	public PedagioResource() {
		inicializar();
	}
	
	private void inicializar() {
		pedagioService = new PedagioService();
	}
	
	@POST
	public String recuperar(String polyline) {
		GsonBuilder gBuilder = new GsonBuilder();
		List<Pedagio> retorno = pedagioService.recuperarPedagios(
				PolyUtil.decode(polyline));
		return gBuilder.create().toJson(retorno);
	}

	@GET
	@Path("/pedagio")
	public List<Pedagio> recuperarGet(String polyline) {
		System.out.println("GET /viagem");
		return pedagioService.recuperarPedagios(PolyUtil.decode(polyline));
	}

}