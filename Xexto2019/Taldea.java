package Xexto2019;

import java.util.ArrayList;

public class Taldea {

	private int kodea;
	private String izena;
	private String herria;
	private String email;
	private String zelaia;
	private ArrayList<Jokalaria> jokalariak = new ArrayList<Jokalaria>();
	
	public Taldea() {
		super();
	}
	
	public Taldea(int kodea, String izena, String herria, String email, String zelaia) {
		super();
		this.kodea = kodea;
		this.izena = izena;
		this.herria = herria;
		this.email = email;
		this.zelaia = zelaia;
	}
	
	public int getKodea() {
		return kodea;
	}
	
	public void setKodea(int kodea) {
		this.kodea = kodea;
	}
	
	public String getIzena() {
		return izena;
	}
	
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getHerria() {
		return herria;
	}
	
	public void setHerria(String herria) {
		this.herria = herria;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getZelaia() {
		return zelaia;
	}
	
	public void setZelaia(String zelaia) {
		this.zelaia = zelaia;
	}
	
	public ArrayList<Jokalaria> getJokalariak() {
		return jokalariak;
	}
	
	public void setJokalariak(ArrayList<Jokalaria> jokalariak) {
		this.jokalariak = jokalariak;
	}
	
	public void gehituJokalaria(Jokalaria jokalaria) {
		this.jokalariak.add(jokalaria);
	}

	public void kenduJokalaria(Jokalaria j) {
		this.jokalariak.remove(j);
	}

	@Override
	public String toString() {
		return "    " + String.format("%03d", kodea)  + "   ||   " + izena + "   ||   " + herria + "   ||   " + email + "   ||   "	+ zelaia;
	}
	
	
	
}
