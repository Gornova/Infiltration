package it.randomtower.infiltration.ui.poi;

import java.awt.image.BufferedImage;

import it.randomtower.infiltration.ui.ImageButton;

@SuppressWarnings("serial")
public class FoodButton extends ImageButton {

	public FoodButton(BufferedImage image, BufferedImage roll, int x, int y, String tooltip) {
		super(image, roll, "", x, y, 32, 32);
		setToolTipText(tooltip);
	}

}
