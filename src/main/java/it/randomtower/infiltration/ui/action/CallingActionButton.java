package it.randomtower.infiltration.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Optional;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.model.faction.FactionEnum;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.ImageButton;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class CallingActionButton extends ImageButton {

	private Actor target;
	private Poi poi;

	public CallingActionButton(BufferedImage image, BufferedImage roll, int x, int y, Scenario scenario,
			MapPanel mapPanel) {
		super(image, roll, scenario.getTooltip(ActionEnum.CALLING), x, y, 32, 32);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (poi.getSecrets().isEmpty()) {
					// TODO: come gestire lato interfaccia che non ci sono più
					// segreti ? fargli fare la quest lo stesso ?
					MapPanel.appendLog(poi.getName(), "No more secrets in " + poi.getName());
					mapPanel.refresh();
					setEnabled(false);
					return;
				}
				if (!scenario.isCalling(poi) && !poi.getType().equals(PoiType.RUIN)) {
					mapPanel.displayChallengePanel(new Challenge(ActionEnum.CALLING, target, null, poi));
					// MapPanel.appendLog("Calling on " + target.getName());
					// scenario.act(ActionEnum.CALLING, target, null, poi);
				} else if (!scenario.isCalling(poi) && poi.getType().equals(PoiType.RUIN)) {
					// if there is a monster, must fight it , otherwise, normal calling
					Optional<Actor> enemyPresence = poi.getActors().stream().filter(
							a -> a.getFaction() != null && a.getFaction().getType().equals(FactionEnum.MONSTERS))
							.findFirst();
					if (enemyPresence.isPresent()) {
						mapPanel.displayFightPanel(target, enemyPresence.get());
					} else {
						mapPanel.displayChallengePanel(new Challenge(ActionEnum.CALLING, target, null, poi));
					}
				}
				mapPanel.refresh();
			}
		});
	}

	public void setModel(Actor model, Poi poi) {
		this.target = model;
		this.poi = poi;
		if (!model.isCorrupted()) {
			setEnabled(false);
		} else {
			setEnabled(true);
		}
	}

}
