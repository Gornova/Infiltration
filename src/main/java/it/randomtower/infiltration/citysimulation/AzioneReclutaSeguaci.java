package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneReclutaSeguaci implements Azione {

	private boolean finita = false;

	@Override
	public void esegui(Simulation game, Actor png) {
		if (png.getMoney() < costoDenaro()) {
			return;
		}
		MapPanel.appendLog(game.getName(),
				String.format(Msg.getText(Msg.ACTION_RECRUIT_FOLLOWERS), png.getName(), costoDenaro()));
		png.addMoney(-costoDenaro());
		png.addFollowers(1);
		finita = true;
	}

	@Override
	public int costoDenaro() {
		return 2;
	}

	@Override
	public int costoInfluenza() {
		return -1;
	}

	@Override
	public boolean completata() {
		return finita;
	}

	@Override
	public int costoSeguaci() {
		return -1;
	}

	@Override
	public int costoArcano() {
		return 0;
	}

}
