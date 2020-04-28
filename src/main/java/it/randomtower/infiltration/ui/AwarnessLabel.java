package it.randomtower.infiltration.ui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class AwarnessLabel extends JLabel {

	public AwarnessLabel(String msg, int x, int y) {
		super(msg);
		setBounds(x, y, 32, 32);
		setToolTipText("Global awarness\nIf reach 5 you lose the game");
	}

	public void refresh(int turn) {
		setText("" + turn + " / 5");
	}

}
