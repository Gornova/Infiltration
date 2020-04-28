package it.randomtower.infiltration.ui;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TurnLabel extends JLabel {

	public TurnLabel(String msg, int x, int y) {
		super(msg);
		setBounds(x, y, 100, 32);
		setToolTipText("Turn count");
	}

	public void refresh(int turn) {
		setText("Turn " + turn);
	}

}
