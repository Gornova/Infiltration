package it.randomtower.infiltration.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.ui.event.EventButton;
import it.randomtower.infiltration.ui.event.EventPanel;

@SuppressWarnings("serial")
public class EventsListPanel extends JPanel {

	private BufferedImage eventImage;
	private List<EventDto> events = new ArrayList<>();
	private EventPanel eventPanel;
	private BufferedImage eventImageRoll;

	public EventsListPanel(EventPanel eventPanel) throws IOException {
		setOpaque(false);
		this.eventPanel = eventPanel;
		this.eventImage = Main.getFileResource("if_16_Exclamation_106231.png");
		this.eventImageRoll = Main.getFileResource("if_16_Exclamation_106231_roll.png");
	}

	private void addButton(String event) {
		EventButton ev = new EventButton(eventImage, eventImageRoll, "", 0, 0, 32, 32, event, eventPanel, this);
		add(ev);
	}

	public void addEvent(String text) {
		EventDto dto = new EventDto();
		dto.text = text;
		if (!events.contains(dto)) {
			events.add(dto);
		}
		refresh();
	}

	public void refresh() {
		removeAll();
		int j = 0;
		for (int i = 0; i < events.size(); i++) {
			addButton(events.get(i).text);
			j++;
			if (j >= 3) {
				break;
			}
		}
		repaint();
	}

	public void removeEvent(String text) {
		EventDto dto = new EventDto();
		dto.text = text;
		events.removeIf(e -> e.text.equals(text));
		refresh();
	}

	public void turn() {
		for (EventDto dto : events) {
			dto.turn++;
		}
		events.removeIf(e -> e.turn > 3);
	}

	private class EventDto {

		public String text = "";
		public int turn = 0;

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof EventDto) {
				return text.equals(((EventDto) obj).text);
			}
			return super.equals(obj);
		}

	}

}
