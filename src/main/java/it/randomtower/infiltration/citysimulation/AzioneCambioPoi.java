package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneCambioPoi implements Azione {

	private boolean finita = false;

	@Override
	public void esegui(Simulation game, Actor png) {
		// randomly select target city
		BasePoi from = Scenario.getInstance().findPoi(game.getName());
		BasePoi target = Scenario.getInstance().findMoveablePoi(game.getName());
		Scenario.getInstance().addAnimation(png, from, target);
		finita = true;
		String log = String.format(Msg.getText(Msg.PNG_MOVE_FROM_TO_CITY), png.getName(), from.getName(),
				target.getName());
		MapPanel.appendLog(from.getName(), log);
	}

	@Override
	public int costoDenaro() {
		return 2;
	}

	@Override
	public int costoInfluenza() {
		return 0;
	}

	@Override
	public boolean completata() {
		return finita;
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
