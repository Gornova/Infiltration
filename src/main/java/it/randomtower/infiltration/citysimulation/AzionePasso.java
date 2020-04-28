package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.model.actor.Actor;

public class AzionePasso implements Azione {

	@Override
	public void esegui(Simulation game, Actor png) {
		// do nothing
	}

	@Override
	public int costoDenaro() {
		return 0;
	}

	@Override
	public int costoInfluenza() {
		return 0;
	}

	@Override
	public boolean completata() {
		return true;
	}

	@Override
	public int costoSeguaci() {
		return 0;
	}

	@Override
	public int costoArcano() {
		return 0;
	}

}
