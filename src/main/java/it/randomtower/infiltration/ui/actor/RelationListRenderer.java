package it.randomtower.infiltration.ui.actor;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.ui.CompoundIcon;
import it.randomtower.infiltration.ui.CompoundIcon.Axis;
import it.randomtower.infiltration.ui.dto.RelationDto;

@SuppressWarnings("serial")
public class RelationListRenderer extends JLabel implements ListCellRenderer<RelationDto> {

	private CompoundIcon ci;
	private CompoundIcon ciRoll;

	public RelationListRenderer() {
		setForeground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		try {
			ci = new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.1_01.jpg")));
			ciRoll = new CompoundIcon(Axis.X_AXIS, new ImageIcon(Main.getFileResource("Icon.1_01.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends RelationDto> list, RelationDto dto, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO: switch on different relationship type and provide different
		// icons
		if (cellHasFocus) {
			setIcon(ciRoll);
		} else {
			setIcon(ci);
		}

		setText(dto.actor.getName());
		setToolTipText(dto.relation.toString());

		return this;
	}

}
