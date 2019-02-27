package Xexto2019;

import java.util.ArrayList;

public class Jardunaldia {
		private String izena;
		
		private ArrayList<Partida> partidak;
		
		public Jardunaldia() {
			this.partidak = new ArrayList<Partida>();
		}



		public Jardunaldia(String izena) {
			this.izena = izena;
			this.partidak = new ArrayList<Partida>();
		}

		
		public String getIzena() {
			return izena;
		}

		public void setIzena(String izena) {
			this.izena = izena;
		}
		
		public ArrayList<Partida> getPartidak() {
			return partidak;
		}
		
		public ArrayList<Taldea> getTaldeak() {
			ArrayList<Taldea> taldeak = new ArrayList<Taldea>();
			for (Partida p : partidak) {
				taldeak.add(p.getTalde1());
				taldeak.add(p.getTalde2());
			}
			return taldeak;
		}

		public void setPartidak(ArrayList<Partida> partidak) {
			this.partidak = partidak;
		}

		@Override
		public String toString() {
			return "Jardunaldia [partidak=" + partidak + "]";
		}

		public void inprimatuTaldeak() {
			String katea="";
			for (Taldea t : this.getTaldeak()) {
				katea = katea + t.getIzena()+ " | ";
			}
			System.out.println(katea);
			
		}
		public boolean eskainiPartida(Partida p) {
			if (this.getTaldeak().contains(p.getTalde1()) || this.getTaldeak().contains(p.getTalde2())) {
				return false;
			}
			else {
				this.partidak.add(p);
				//System.out.println("JP: " +  p + partidak.size());
				return true;
			}
			

		}
		
}
