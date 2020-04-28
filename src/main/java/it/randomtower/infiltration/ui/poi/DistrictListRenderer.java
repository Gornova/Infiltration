package it.randomtower.infiltration.ui.poi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.ui.CompoundIcon;
import it.randomtower.infiltration.ui.CompoundIcon.Axis;

@SuppressWarnings("serial")
public class DistrictListRenderer extends JPanel implements ListCellRenderer<District> {

	private JLabel label;
	private JLabel labelSecretIcon;
	private BufferedImage image;

	public DistrictListRenderer() throws IOException {
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		label = new JLabel();
		label.setForeground(Color.WHITE);
		add(label, BorderLayout.CENTER);

		labelSecretIcon = new JLabel();
		labelSecretIcon.setForeground(Color.WHITE);
		labelSecretIcon.setVisible(false);
		add(labelSecretIcon, BorderLayout.EAST);

		image = Main.getFileResource("clues.png");
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends District> list, District district, int index,
			boolean isSelected, boolean cellHasFocus) {

		BufferedImage testImage = null;
		try {
			testImage = Main.getFileResource("Icon.2_45.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}

		CompoundIcon testci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(testImage));

		String text = district.nome
				+ (district.padrone != null && !district.padrone.isEmpty() ? " owned by " + district.padrone : "");
		if (district.secretAvailable) {
			CompoundIcon ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(image));
			labelSecretIcon.setIcon(ci);
			labelSecretIcon.setVisible(true);
			labelSecretIcon.setToolTipText(
					"An hidden secret of the world is here\nYou should manipulate an actor to investigate here");
		} else {
			labelSecretIcon.setIcon(null);
			labelSecretIcon.setVisible(false);
		}
		label.setText(text);

		return this;
	}

}
