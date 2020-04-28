package it.randomtower.infiltration.ui.map;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.ui.ImageButton;

@SuppressWarnings("serial")
public class AgentMapButton extends ImageButton {

	private BasePoi poi;
	private BufferedImage imageActive;
	private BufferedImage image;

	public AgentMapButton(BasePoi p, BufferedImage image, BufferedImage imageActive) {
		super(image, image, "No actor corrupted", (int) p.getX(), (int) p.getY(), 16, 32);
		this.poi = p;
		this.image = image;
		this.imageActive = imageActive;
		setBounds((int) p.getX() + 64, (int) p.getY(), 16, 32);
	}

	public void refresh() {
		if (poi.getActors().stream().anyMatch(a -> a.isCorrupted())) {
			setIcon(new ImageIcon(imageActive));
			setToolTipText("Actor corrupted");
		} else {
			setIcon(new ImageIcon(image));
			setToolTipText("No actor corrupted");
		}
	}

}
