package Xexto2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Liga {
	private String ligaIzena;
	private ArrayList<Taldea> taldeak = new ArrayList<Taldea>();
	private ArrayList<Partida> partidak  = new ArrayList<Partida>();
	private ArrayList<Jardunaldia> jardunaldiak = new ArrayList<Jardunaldia>();
	private ArrayList <Erabiltzailea> erabiltzaileak = new ArrayList <Erabiltzailea>();
	
	public Liga() {
	}

	public Liga(String ligaIzena) {
		this.ligaIzena = ligaIzena;
		this.taldeak = new ArrayList<Taldea>();
		this.jardunaldiak = new ArrayList<Jardunaldia>();
	}
	
	public Liga(String ligaIzena, ArrayList<Taldea> taldeak, ArrayList<Jardunaldia> jardunaldiak) {
		super();
		this.ligaIzena = ligaIzena;
		this.taldeak = taldeak;
		this.jardunaldiak = jardunaldiak;
	}

	public String getLigaIzena() {
		return ligaIzena;
	}

	public void setLigaIzena(String ligaIzena) {
		this.ligaIzena = ligaIzena;
	}

	public ArrayList<Taldea> getTaldeak() {
		return taldeak;
	}

	public void setTaldeak(ArrayList<Taldea> taldeak) {
		this.taldeak = taldeak;
	}

	public ArrayList<Jardunaldia> getJardunaldiak() {
		return jardunaldiak;

	}

	public void setJardunadiak(ArrayList<Jardunaldia> jardunaldiak) {
	}
	public ArrayList<Erabiltzailea> getErabiltzaileak() {
		return erabiltzaileak;
	}
	public void setErabiltzaileak(ArrayList<Erabiltzailea> erabiltzailea) {
		this.erabiltzaileak = erabiltzailea;
	}
	
	public  void gehituErabiltzailea(Erabiltzailea erabiltzailea) {
		this.erabiltzaileak.add(erabiltzailea);
	}
	
		public void kenduErabiltzailea(Erabiltzailea e) {
		this.erabiltzaileak.remove(e);
	}
	
	public void gehituTaldea(Taldea t) {
		this.taldeak.add(t);
	}
	public void kenduTaldea(Taldea t) {
		this.taldeak.remove(t);
	}
	
	public void inprimatuTaldeak() {
		for (Taldea t : taldeak) {
			System.out.println(t);
		}
	}
	
	/*
	private ArrayList<Partida> sortuPartidak() {
		partidak.clear();
		for (Taldea t1 : taldeak) {
			for (Taldea t2 : taldeak) {
				if (!t1.equals(t2)) {
					//Garbi
					partidak.add(new Partida(t1,t2));
					
					
				}
			}
		}
		Collections.shuffle(partidak);
		//System.out.println("Partidak: " + partidak.size());
		return partidak;
	}
	*/
	
	public void sortuJardunaldiak() {
		//Round Robin Tournament
		ArrayList<Taldea> etxekoak = new ArrayList<Taldea>(taldeak.subList(0 , (taldeak.size()/2)));
		ArrayList<Taldea> kanpokoak = new ArrayList<Taldea>(taldeak.subList(taldeak.size()/2 , taldeak.size()));
		Collections.reverse(kanpokoak);
		
		jardunaldiak.clear();
		//Ausazko emaitzekin
		Random r = new Random();
		int low = 60;
		int high = 120;
		int jardunaldiKopurua = taldeak.size()*(taldeak.size()-1)/(taldeak.size()/2);
		for (int i = 0; i < jardunaldiKopurua;i++) {
			Jardunaldia jardunaldiHau = new Jardunaldia("J" + String.format("%03d", i));
			for (int j = 0; j < etxekoak.size() ; j++) {
				//Garbi
				//Partida p = new Partida(etxekoak.get(j), kanpokoak.get(j));
				
				//Ausazko emaitzak;
				int emaitza1 = r.nextInt(high-low) + low;
				int emaitza2 = r.nextInt(high-low) + low;
				if (emaitza1 == emaitza2) {
					emaitza1++;
				}
				Partida p = new Partida(etxekoak.get(j), kanpokoak.get(j), emaitza1, emaitza2);
				
				jardunaldiHau.eskainiPartida(p);
				//System.out.println(p);
			}
			jardunaldiak.add(jardunaldiHau);
			
			//Errotazioa
			ArrayList<Taldea> aux = new ArrayList<Taldea>();
			aux.add(etxekoak.get(0));
			etxekoak.remove(0);
			aux.add(kanpokoak.get(0));
			kanpokoak.remove(0);
			kanpokoak.add(etxekoak.get(etxekoak.size()-1));
			etxekoak.remove(etxekoak.size()-1);
			aux.addAll(etxekoak);
			etxekoak.clear();
			etxekoak.addAll(aux);
		}
		
	}
	
	/*
	public void sortuJardunaldiak_zaharra() {
		int saiakerak=1;
		while(!saiatuJardunaldiak()) {
			System.out.println("SAIAKERAK:" +saiakerak);
			saiakerak++;
		}
	}
	*/
	
	/*
	public boolean saiatuJardunaldiak() {
		sortuPartidak();
			
		
		//Hasieratu jardunaldiak
		jardunaldiak.clear();
		int jardunaldiKopurua = partidak.size()/(taldeak.size()/2);
		for (int i = 0; i < jardunaldiKopurua;i++) {
			jardunaldiak.add(new Jardunaldia("J"+(i+1)));
			ordenatuPartidak(partidak.get(partidak.size()-1));
		}
		//System.out.println("Jardunaldiak: " + jardunaldiak.size());
		while(partidak.size()>0) {
			
			boolean sartua =false;
			for (int i = 0; i < jardunaldiak.size(); i++) {
				//System.out.println("J"+i+ " / "+jardunaldiak.size());
				Jardunaldia j = jardunaldiak.get(i);
				Partida p = partidak.get(0);

				if(j.eskainiPartida(p)) {
					//eskainipartidak true ematen badu partida hartu du
					partidak.remove(0);
					sartua=true;
				}
				
			}
			if (!sartua) {
				System.out.println("KATASTROFE: " + partidak.size() + " partida soberan");
				
				for (Jardunaldia j : jardunaldiak) {
					j.inprimatuTaldeak();
				}
				return false;
			}			
		}
		
		for (Jardunaldia j : jardunaldiak) {
			System.out.println("J");
			for (Partida p : j.getPartidak()) {
				System.out.println(p);
			}
		}
		return true;
	}
	*/
	
	/*
	private Partida aukeratuPartida(ArrayList<Partida> jardunaldia){
		
		ArrayList<Taldea> jolastendu = new ArrayList<Taldea>();
		for (Partida p : jardunaldia) {						
			Taldea t1 = p.getTalde1();
			Taldea t2 = p.getTalde2();
			jolastendu.add(t1);
			jolastendu.add(t2);
		}
		
		Partida aukeratua = new Partida();
		boolean baliodu = true;
		//System.out.println("A:" + partidak.size());
		do {	
			//Hartu
			aukeratua = partidak.get(0);
			partidak.remove(0);
			baliodu = true;
			//Egiaztatu
			if (jolastendu.contains(aukeratua.getTalde1()) || jolastendu.contains(aukeratua.getTalde2())) {
				baliodu=false;
				partidak.add(aukeratua);
			}
					
		}while(!baliodu);
		
		return aukeratua;
	}
	*/
	
	/*
	private void ordenatuPartidak(Partida x) {

		Taldea t1 =x.getTalde1();
		Taldea t2 =x.getTalde2();
		ArrayList<Partida> bloke0 = new ArrayList<Partida>();
		ArrayList<Partida> bloke1 = new ArrayList<Partida>();
		ArrayList<Partida> bloke2 = new ArrayList<Partida>();
		ArrayList<Partida> bloke3 = new ArrayList<Partida>();
		ArrayList<Partida> bloke4 = new ArrayList<Partida>();
			
		for (int i = 0 ; i < partidak.size(); i++) {
			Partida p = partidak.get(i);
	
			if(p.getTalde2().equals(t2)) {
				bloke4.add(p);
			} 
			else if (p.getTalde1().equals(t2)) {			
				bloke3.add(p);
			}
			else if (p.getTalde1().equals(t1)) {
				bloke2.add(p);
			}
			else if (p.getTalde2().equals(t1)) {			
				bloke1.add(p);
			}
			else {
				bloke0.add(p);
			}
			
		}
	
		partidak = bloke0;
		partidak.addAll(bloke1);
		partidak.addAll(bloke2);
		partidak.addAll(bloke3);
		partidak.addAll(bloke4);
	}
	*/
	
	
	private void inprimatuJardunaldiak(ArrayList<Jardunaldia> jardunaldiZerrenda) {	
		int x=1;
		 for (Jardunaldia j : jardunaldiak) {
			System.out.println("J"+String.format("%03d", x));
			 for (Partida p : j.getPartidak()) {
				 System.out.println(p);
			 }
			 x++;
		 }
	}
	
	
	
}
