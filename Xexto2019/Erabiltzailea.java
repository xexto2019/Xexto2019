package Xexto2019;

public class Erabiltzailea {
	private String izena;
	private String pasahitza;
	private String erabiltzaileMota;
	
	public Erabiltzailea() {
		super();
	}
	
	public Erabiltzailea(String izena, String pasahitza, String erabiltzaileMota) {
		super();
		this.setIzena(izena);
		this.setPasahitza(pasahitza);
		this.setErabiltzaileMota(erabiltzaileMota);
	}
	
	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	public String getErabiltzaileMota() {
		return erabiltzaileMota;
	}
	public void setErabiltzaileMota(String erabiltzaileMota) {
		this.erabiltzaileMota = erabiltzaileMota;
	}
		
	@Override
	public String toString() {
		return  izena + ":  " + erabiltzaileMota;
	}
	

}
