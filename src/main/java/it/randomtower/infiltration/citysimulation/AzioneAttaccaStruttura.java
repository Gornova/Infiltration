package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneAttaccaStruttura implements Azione {

	private boolean finita = false;
	private District struttura;

	@Override
	public void esegui(Simulation game, Actor png) {
		String log = String.format(Msg.getText(Msg.ACTION_ATTACK_STRUCTURE), png.getName(), struttura.nome);
		png.addQuestLog(log);
		MapPanel.appendLog(game.getName(), log);
		Combat.combat(game, png, struttura);
		finita = true;
	}

	public void setPlace(District struttura) {
		this.struttura = struttura;
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
