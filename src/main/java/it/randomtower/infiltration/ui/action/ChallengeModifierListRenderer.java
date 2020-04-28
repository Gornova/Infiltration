package it.randomtower.infiltration.ui.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.model.actor.tag.Tag;
import it.randomtower.infiltration.ui.CompoundIcon;
import it.randomtower.infiltration.ui.CompoundIcon.Axis;

@SuppressWarnings("serial")
public class ChallengeModifierListRenderer extends JPanel implements ListCellRenderer<Tag> {

	private JLabel label;
	private JLabel name;

	public ChallengeModifierListRenderer() {
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		label = new JLabel();
		label.setForeground(Color.WHITE);
		add(label, BorderLayout.CENTER);

		name = new JLabel();
		name.setVisible(false);
		name.setForeground(Color.WHITE);
		add(name, BorderLayout.EAST);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tag> list, Tag tag, int index, boolean isSelected,
			boolean cellHasFocus) {
		try {
			CompoundIcon ci;
			if (tag.isBonus()) {
				ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.3_77.jpg")));
			} else {
				ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.6_81.jpg")));
			}
			label.setIcon(ci);
			setToolTipText(tag.getDescription());
		} catch (IOException e) {
			e.printStackTrace();
		}
		label.setText(tag.getName());
		return this;
	}

}
