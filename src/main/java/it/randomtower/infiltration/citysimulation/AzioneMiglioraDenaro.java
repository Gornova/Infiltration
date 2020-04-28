package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneMiglioraDenaro implements Azione {

	private boolean finita = false;

	@Override
	public void esegui(Simulation game, Actor png) {
		MapPanel.appendLog(game.getName(),
				String.format(Msg.getText(Msg.ACTION_IMPROVE_MONEY), png.getName(), costoArcano(), 1));
		png.addArcane(-costoArcano());
		png.setMoneyBonus(png.getMoneyBonus() + 1);
		finita = true;
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
	public int costoSeguaci() {
		return 0;
	}

	@Override
	public boolean completata() {
		return finita;
	}

	@Override
	public int costoArcano() {
		return 5;
	}

}
