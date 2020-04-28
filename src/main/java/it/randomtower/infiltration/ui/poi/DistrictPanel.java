package it.randomtower.infiltration.ui.poi;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.citysimulation.DistrictBuilder;
import it.randomtower.infiltration.ui.CompoundIcon;
import it.randomtower.infiltration.ui.CompoundIcon.Axis;

public class DistrictPanel extends JPanel {
	private JLabel marketLabel;
	private JLabel docksLabel;
	private JLabel cathedralLabel;
	private JLabel castleLabel;
	private JLabel clueLabel;
	private JLabel marketOwnerLabel;
	private JLabel docksOwnerLabel;
	private JLabel cathedralOwnerLabel;
	private JLabel castleOwnerLabel;

	public DistrictPanel() {
		setLayout(null);
		setBackground(new Color(74, 80, 80));

		BufferedImage testImage = null;
		try {
			marketLabel = new JLabel();
			marketLabel.setIcon(new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.3_84.jpg"))));
			marketLabel.setToolTipText("Market district");
			marketLabel.setBounds(10, 11, 33, 32);
			add(marketLabel);

			docksLabel = new JLabel();
			docksLabel.setIcon(new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.7_46.jpg"))));
			docksLabel.setToolTipText("Docks district");
			docksLabel.setBounds(10, 97, 33, 32);
			add(docksLabel);

			cathedralLabel = new JLabel();
			cathedralLabel.setIcon(new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.2_45.jpg"))));
			cathedralLabel.setBounds(10, 54, 33, 32);
			cathedralLabel.setToolTipText("Cathedral district");
			add(cathedralLabel);

			castleLabel = new JLabel();
			castleLabel.setIcon(new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.3_17.jpg"))));
			castleLabel.setBounds(10, 140, 33, 32);
			castleLabel.setToolTipText("Castle district");
			add(castleLabel);

			clueLabel = new JLabel();
			clueLabel.setIcon(new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("clues.png"))));
			clueLabel.setBounds(170, 11, 33, 32);
			clueLabel.setToolTipText("Hidden knowledge in this district");
			clueLabel.setVisible(false);
			add(clueLabel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		marketOwnerLabel = new JLabel("No owner");
		marketOwnerLabel.setForeground(Color.WHITE);
		marketOwnerLabel.setBounds(54, 23, 114, 14);
		add(marketOwnerLabel);

		docksOwnerLabel = new JLabel("No owner");
		docksOwnerLabel.setForeground(Color.WHITE);
		docksOwnerLabel.setBounds(53, 105, 114, 14);
		add(docksOwnerLabel);

		cathedralOwnerLabel = new JLabel("No owner");
		cathedralOwnerLabel.setForeground(Color.WHITE);
		cathedralOwnerLabel.setBounds(53, 62, 115, 14);
		add(cathedralOwnerLabel);

		castleOwnerLabel = new JLabel("No owner");
		castleOwnerLabel.setForeground(Color.WHITE);
		castleOwnerLabel.setBounds(53, 148, 115, 14);
		add(castleOwnerLabel);
	}

	public void refresh(List<District> districts) {
		if (districts == null) {
			return;
		}
		for (District district : districts) {
			int ty = 0;
			String text = district.padrone != null && !district.padrone.isEmpty() ? "owner " + district.padrone
					: "No owner";
			if (district.nome.equals(DistrictBuilder.CASTLE)) {
				castleOwnerLabel.setText(text);
				ty = castleOwnerLabel.getY();
			}
			if (district.nome.equals(DistrictBuilder.CATHEDRAL)) {
				cathedralOwnerLabel.setText(text);
				ty = cathedralOwnerLabel.getY();
			}
			if (district.nome.equals(DistrictBuilder.DOCKS)) {
				docksOwnerLabel.setText(text);
				ty = docksOwnerLabel.getY();
			}
			if (district.nome.equals(DistrictBuilder.MARKET)) {
				marketOwnerLabel.setText(text);
				ty = marketOwnerLabel.getY();
			}
			if (district.secretAvailable && ty > 0) {
				clueLabel.setBounds(170, ty, 33, 32);
				clueLabel.setVisible(true);
			}
		}
	}
}
