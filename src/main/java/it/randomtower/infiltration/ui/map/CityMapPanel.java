package it.randomtower.infiltration.ui.map;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.randomtower.infiltration.model.poi.BasePoi;

@SuppressWarnings("serial")
public class CityMapPanel extends JPanel {

	private BasePoi poi;

	public CityMapPanel(BasePoi p, int x, int y) {
		setBorder(null);
		setOpaque(false);
		this.poi = p;

		JLabel cityLabel = new JLabel(p.getName());
		Font font = new Font("Tahoma", Font.BOLD, 15);
		cityLabel.setFont(font);
		cityLabel.setOpaque(true);
		cityLabel.setForeground(new Color(209, 202, 183));
		cityLabel.setBackground(new Color(104, 99, 67));
		cityLabel.setBounds((int) p.getX(), (int) p.getY() + 64, 64, 32);
		add(cityLabel, 0);

		setBounds(x, y, 164, 164);
	}

	public void refresh() {
		if (!poi.isHidden()) {
			setVisible(true);
		}
	}

}
