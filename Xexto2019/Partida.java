package Xexto2019;

public class Partida {
	private Taldea talde1;
	private Taldea talde2;
	private int emaitza1;
	private int emaitza2;
	
	public Partida() {
	}
	
	public Partida(Taldea talde1, Taldea talde2) {
		this.talde1 = talde1;
		this.talde2 = talde2;
	}

	public Partida(Taldea talde1, Taldea talde2, int emaitza1, int emaitza2) {
		this.talde1 = talde1;
		this.talde2 = talde2;
		this.emaitza1 = emaitza1;
		this.emaitza2 = emaitza2;
	}


	public Taldea getTalde1() {
		return talde1;
	}

	public void setTalde1(Taldea talde1) {
		this.talde1 = talde1;
	}

	public Taldea getTalde2() {
		return talde2;
	}

	public void setTalde2(Taldea talde2) {
		this.talde2 = talde2;
	}


	public int getEmaitza1() {
		return emaitza1;
	}

	public void setEmaitza1(int emaitza1) {
		this.emaitza1 = emaitza1;
	}

	public int getEmaitza2() {
		return emaitza2;
	}

	public void setEmaitza2(int emaitza2) {
		this.emaitza2 = emaitza2;
	}
	
	public int[] getPuntuak() {
		int[] puntuak = new int[2];
		
		if (emaitza1> emaitza2) {
			puntuak[0]=2;
			puntuak[1]=1;
		}
		else {
			puntuak[0]=1;
			puntuak[1]=2;
		}
		
		return puntuak;
	}
	
	@Override
	public String toString() {
		return talde1.getIzena() + " || " + talde2.getIzena() + " || " + emaitza1	+ " || " + emaitza2;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((talde1 == null) ? 0 : talde1.hashCode());
		result = prime * result + ((talde2 == null) ? 0 : talde2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (talde1 == null) {
			if (other.talde1 != null)
				return false;
		} else if (!talde1.equals(other.talde1))
			return false;
		if (talde2 == null) {
			if (other.talde2 != null)
				return false;
		} else if (!talde2.equals(other.talde2))
			return false;
		return true;
	}
	
	
}
