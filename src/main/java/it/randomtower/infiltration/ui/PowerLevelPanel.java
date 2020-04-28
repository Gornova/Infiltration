package it.randomtower.infiltration.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.ui.poi.CircleButton;

@SuppressWarnings("serial")
public class PowerLevelPanel extends JPanel {

	public PowerLevelPanel(int x, int y) {
		setBorder(null);
		setOpaque(false);
		setBounds(x, y, 48, 200);
	}

	public void setPower(int power) {
		removeAll();
		setToolTipText("Ancient power level " + power + " / 5 \n At 5 you win");
		try {
			int j = 0;
			for (int i = 0; i < 5; i++) {
				BufferedImage cbi = null;
				BufferedImage cbiRoll = null;
				if (j < power) {
					cbi = Main.getFileResource("circle-blue-full.png");
					cbiRoll = Main.getFileResource("circle-blue-full_roll.png");
				} else {
					cbi = Main.getFileResource("circle-blue.png");
					cbiRoll = Main.getFileResource("circle-blue_roll.png");
				}
				j++;
				CircleButton circleButton = new CircleButton(cbi, cbiRoll, 10, 100 + i * 48);
				circleButton.setToolTipText("Ancient power level " + power + " / 5 \n At 5 you win");
				add(circleButton, 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

}
