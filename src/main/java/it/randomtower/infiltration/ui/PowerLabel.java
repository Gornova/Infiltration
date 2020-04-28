package it.randomtower.infiltration.ui;

import javax.swing.JLabel;

import it.randomtower.infiltration.scenario.Scenario;

@SuppressWarnings("serial")
public class PowerLabel extends JLabel {

	private Scenario scenario;

	public PowerLabel(String msg, int x, int y, Scenario scenario) {
		super(msg);
		setBounds(x, y, 32, 32);
		setToolTipText("Ancient current available power\nCan be increased finding artifacts");
		this.scenario = scenario;
	}

	public void refresh(int power) {
		setText("" + power + " / 5");
	}

}
