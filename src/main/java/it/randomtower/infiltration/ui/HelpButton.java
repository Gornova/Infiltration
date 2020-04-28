package it.randomtower.infiltration.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.ui.event.EventPanel;

@SuppressWarnings("serial")
public class HelpButton extends ImageButton {

	private EventPanel eventPanel;

	public HelpButton(BufferedImage image, BufferedImage imageRoll, int x, int y, EventPanel eventPanel) {
		super(image, imageRoll, "Help", x, y, 32, 32);
		this.eventPanel = eventPanel;

		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				displayHelp();
			}
		});
	}

	public void displayHelp() {
		eventPanel.displayEvent(Msg.getText(Msg.WELCOME_MSG), "Tutorial");
	}

}
