package it.randomtower.infiltration.ui;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class ImageButton extends JButton {

	public ImageButton(Image image, Image onOverImage, String tooltip, int x, int y, int w, int h) {
		super(new ImageIcon(image));

		setBounds(x, y, w, h);
		setBorderPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorder(null);
		setOpaque(false);
		setContentAreaFilled(false);
		setRolloverIcon(new ImageIcon(onOverImage));
		if (tooltip != null && !tooltip.isEmpty()) {
			setToolTipText(tooltip);
		}
	}

	public ImageButton(String tooltip, int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setBorderPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		setIconTextGap(0);
		setBorder(null);
		setOpaque(false);
		setContentAreaFilled(false);
		if (tooltip != null && !tooltip.isEmpty()) {
			setToolTipText(tooltip);
		}
	}

	public void setIcon(Image img) {
		setIcon(new ImageIcon(img));
		// setRolloverIcon(new ImageIcon(onOverImage));
	}

}
