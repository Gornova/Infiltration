package it.randomtower.infiltration.citysimulation;
public class Miglioramento {

	public int costo;
	public TYPE type;

	public enum TYPE {
		SEGUACI, DENARO;
	}

	public Miglioramento(int costo, TYPE type) {
		this.costo = costo;
		this.type = type;
	}

}
