package it.randomtower.infiltration.ui.poi;

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
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.CompoundIcon;
import it.randomtower.infiltration.ui.CompoundIcon.Axis;

@SuppressWarnings("serial")
public class ActorListRenderer extends JPanel implements ListCellRenderer<Actor> {

	private JLabel label;
	private JLabel currentActionLabelCounter;

	public ActorListRenderer() {
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BorderLayout(0, 0));
		setOpaque(false);
		label = new JLabel();
		label.setForeground(Color.WHITE);
		add(label, BorderLayout.CENTER);

		currentActionLabelCounter = new JLabel();
		currentActionLabelCounter.setVisible(false);
		currentActionLabelCounter.setForeground(Color.WHITE);
		currentActionLabelCounter.setToolTipText("Currenct action turns");
		add(currentActionLabelCounter, BorderLayout.EAST);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Actor> list, Actor actor, int index,
			boolean isSelected, boolean cellHasFocus) {
		try {

			if (actor.isDoingActions()) {
				currentActionLabelCounter
						.setText(actor.getCurrentActionType().toString() + " " + actor.getCurrentActionCounter());
				currentActionLabelCounter.setVisible(true);
			} else {
				currentActionLabelCounter.setVisible(false);
			}

			// TODO: add a second icon for every actor?
			if (actor.isCorrupted()) {
				if (cellHasFocus) {
					CompoundIcon ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(actor.getFaction().getImageRoll()));
					label.setIcon(ci);
				} else {
					CompoundIcon ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(actor.getFaction().getImage()));
					label.setIcon(ci);
				}
				setToolTipText(actor.getDescription());
			} else {
				CompoundIcon ci;
				if (cellHasFocus) {
					ci = new CompoundIcon(Axis.X_AXIS,
							new ImageIcon(Main.getFileResource("if_user_man_678132_roll.png")));
				} else {
					ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("if_user_man_678132.png")));
				}
				label.setIcon(ci);
				setToolTipText(null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		label.setText(actor.getName());
		// repaint();
		return this;
	}

}
