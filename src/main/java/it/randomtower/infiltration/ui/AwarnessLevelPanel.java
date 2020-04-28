package it.randomtower.infiltration.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.ui.poi.CircleButton;

//TODO: refactor in one panel with PowerLevelPanel ? 
@SuppressWarnings("serial")
public class AwarnessLevelPanel extends JPanel {

	public AwarnessLevelPanel(int x, int y) {
		setBorder(null);
		setOpaque(false);
		setBounds(x, y, 48, 200);
	}

	public void setAwarness(int awarness) {
		removeAll();
		setToolTipText("World awarness " + awarness + " / 5 \n At 5 you lose");
		try {
			int j = 0;
			for (int i = 0; i < 5; i++) {
				BufferedImage cbi = null;
				BufferedImage cbiRoll = null;
				if (j < awarness) {
					cbi = Main.getFileResource("circle-red-full.png");
					cbiRoll = Main.getFileResource("circle-red-full_roll.png");
				} else {
					cbi = Main.getFileResource("circle-red.png");
					cbiRoll = Main.getFileResource("circle-red_roll.png");
				}
				j++;
				CircleButton circleButton = new CircleButton(cbi, cbiRoll, 10, 100 + i * 48);
				circleButton.setToolTipText("World awarness " + awarness + " / 5 \n At 5 you lose");
				add(circleButton, 0);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}

}
