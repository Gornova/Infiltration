package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.model.actor.Actor;

public interface Azione {

	public void esegui(Simulation game, Actor png);

	public int costoDenaro();

	public int costoInfluenza();

	public int costoSeguaci();

	public int costoArcano();

	public boolean completata();

}
