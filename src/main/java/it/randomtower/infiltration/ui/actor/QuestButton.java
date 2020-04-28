package it.randomtower.infiltration.ui.actor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.ImageButton;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class QuestButton extends ImageButton {

	private Actor target;

	public QuestButton(BufferedImage image, BufferedImage roll, int x, int y, Scenario scenario, MapPanel mapPanel) {
		super(image, roll, "", x, y, 32, 32);
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapPanel.displayActorLogPanel(true, target);
			}
		});
	}

	public void setModel(Actor model, Poi poi) {
		this.target = model;
		if (!model.isCorrupted()) {
			setEnabled(false);
		} else {
			setEnabled(true);
		}
	}

}
