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
public class ManipulateActionButton extends ImageButton {

	private Poi poi;
	private Actor target;

	public ManipulateActionButton(BufferedImage image, BufferedImage roll, int x, int y, Scenario scenario) {
		super(image, roll, scenario.getTooltip(ActionEnum.MANIPULATE), x, y, 32, 32);

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MapPanel.appendLog(poi.getName(),
						"Started manipulation on " + target.getName() + " in " + poi.getName());
			}
		});
	}

	public void setModel(Actor model, Poi poi) {
		this.target = model;
		this.poi = poi;
	}

}
