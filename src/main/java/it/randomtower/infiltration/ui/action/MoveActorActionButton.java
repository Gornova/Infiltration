package it.randomtower.infiltration.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.ImageButton;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class MoveActorActionButton extends ImageButton {

	private Actor target;
	private Poi poi;

	public MoveActorActionButton(BufferedImage image, BufferedImage roll, int x, int y, Scenario scenario,
			MapPanel mapPanel) {
		super(image, roll, scenario.getTooltip(ActionEnum.MOVING), x, y, 32, 32);
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
				if (!scenario.isCalling(poi)) {
					mapPanel.displayMoveActionPanel(target, poi);
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
