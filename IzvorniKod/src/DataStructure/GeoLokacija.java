package DataStructure;

public class GeoLokacija {

	private float geoSirina;
	private float geoDuziina;
	private String labela;
	
	
	public GeoLokacija (float geoSirina, float geoDuziina, String labela) {
		
		this.geoSirina = geoSirina;
		this.geoDuziina = geoDuziina;
		this.labela = labela;
	}

	public float getGeoSirina () {
		
		return this.geoSirina;
	}

	public float getGeoDuziina () {
		
		return this.geoDuziina;
	}
	
	public String getLabela () {
		
		return this.labela;
	}
	
	public void setLabela (String novaLabela) {
		
		this.labela = novaLabela;
	}
}
