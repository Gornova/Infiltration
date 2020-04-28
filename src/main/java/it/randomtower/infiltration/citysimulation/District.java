package it.randomtower.infiltration.citysimulation;

public class District implements Comparable<District> {

	public String nome;
	public int influenza;
	public int difesa;
	public String padrone = "";
	public boolean secretAvailable = false;

	@Override
	public String toString() {
		return String.format("--LUOGO: %s, padrone = %s , costoInfluenza= %d, valoreDifesa = %d", nome, padrone,
				influenza, difesa);
	}

	@Override
	public int compareTo(District o) {
		return o.nome.compareTo(this.nome);
	}

}
