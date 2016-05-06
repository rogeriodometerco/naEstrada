package model;

public class Pedagio {
	private String concessionaria;
	private String praca;
	private double valor;
	private LatLng latLng;
	private String municipio;
	
	public Pedagio(String concessionaria, String praca, double valor, LatLng latLng, String municipio) {
		this.concessionaria = concessionaria;
		this.praca = praca;
		this.valor = valor;
		this.latLng = latLng;
		this.municipio = municipio;
	}

	public String getConcessionaria() {
		return concessionaria;
	}
	
	public void setConcessionaria(String concessionaria) {
		this.concessionaria = concessionaria;
	}
	
	public String getPraca() {
		return praca;
	}

	public void setPraca(String praca) {
		this.praca = praca;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicípio(String municipio) {
		this.municipio = municipio;
	}

	
}