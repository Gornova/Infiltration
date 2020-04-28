package it.randomtower.infiltration.ui.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import it.randomtower.infiltration.ui.EventsListPanel;
import it.randomtower.infiltration.ui.ImageButton;

@SuppressWarnings("serial")
public class EventButton extends ImageButton {

	private String event;
	private EventPanel eventPanel;
	private EventsListPanel eventListPanel;

	public EventButton(BufferedImage image, BufferedImage onOverImage, String tooltip, int x, int y, int w, int h,
			String event, EventPanel eventPanel, EventsListPanel eventListPanel) {
		super(image, onOverImage, tooltip, x, y, w, h);
		this.eventPanel = eventPanel;
		this.event = event;
		this.eventListPanel = eventListPanel;

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventPanel.displayEvent(event, "Event");
				eventListPanel.removeEvent(event);
			}
		});
	}

}
