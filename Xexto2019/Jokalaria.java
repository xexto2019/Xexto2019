package Xexto2019;

public class Jokalaria {

	private String nan;
	private String abizena;
	private String izena;
	
	public Jokalaria() {
	}
	
	public Jokalaria(String nan, String abizena, String izena) {
		this.nan = nan;
		this.abizena = abizena;
		this.izena = izena;
	}
	public String getNan() {
		return nan;
	}
	public void setNan(String nan) {
		this.nan = nan;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}

	@Override
	public String toString() {
		return nan + " || " + abizena + " || "  + izena;
	}
	
}
