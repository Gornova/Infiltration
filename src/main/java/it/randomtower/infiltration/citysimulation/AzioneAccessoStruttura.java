package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneAccessoStruttura implements Azione {

	private boolean finita = false;
	private District struttura;

	public void setPlace(District struttura) {
		this.struttura = struttura;
	}

	@Override
	public void esegui(Simulation game, Actor png) {
		if (png.getInfluence() < costoInfluenza()) {
			return;
		}
		MapPanel.appendLog(game.getName(), String.format(Msg.getText(Msg.ACTION_ACCESS_STRUCTURE), png.getName(),
				costoInfluenza(), struttura.nome));
		png.addInfluence(-costoInfluenza());
		struttura.padrone = png.getName();
		finita = true;
	}

	@Override
	public int costoDenaro() {
		return 0;
	}

	@Override
	public int costoInfluenza() {
		return struttura.influenza;
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
