package it.randomtower.infiltration.ui.event;

import java.awt.image.BufferedImage;

import it.randomtower.infiltration.ui.ImageButton;

@SuppressWarnings("serial")
public class EventImage extends ImageButton {

	public EventImage(BufferedImage image, BufferedImage onOverImage, String tooltip, int x, int y, int w, int h) {
		super(image, onOverImage, tooltip, x, y, w, h);
	}

}
