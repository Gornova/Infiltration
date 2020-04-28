package it.randomtower.infiltration.ui.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.ImageButton;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class CorruptActionButton extends ImageButton {

	private Poi poi;
	private Scenario scenario;
	private Actor target;
	private MapPanel mapPanel;

	// TODO: guava to avoit to pass scenario and mappanel?
	public CorruptActionButton(BufferedImage image, BufferedImage roll, int x, int y, Scenario scenario,
			MapPanel mapPanel) {
		super(image, roll, scenario.getTooltip(ActionEnum.CORRUPT), x, y, 32, 32);
		this.scenario = scenario;
		this.mapPanel = mapPanel;
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mapPanel.displayChallengePanel(new Challenge(ActionEnum.CORRUPT, target, null, poi));
			}
		});
	}

	public void setModel(Actor model, Poi poi) {
		this.target = model;
		this.poi = poi;
	}

}
