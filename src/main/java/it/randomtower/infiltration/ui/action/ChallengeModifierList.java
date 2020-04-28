package it.randomtower.infiltration.ui.action;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.randomtower.infiltration.model.actor.tag.Tag;

@SuppressWarnings("serial")
public class ChallengeModifierList extends JList {

	public ChallengeModifierList() {
		setCellRenderer(new ChallengeModifierListRenderer());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object elem = getSelectedValue();
					if (elem != null) {
					}
				}
			}
		});
	}

	public void setCurrentModel(List<Tag> tags) {
		DefaultListModel<Tag> listModel = new DefaultListModel<>();
		for (Tag tag : tags) {
			listModel.addElement(tag);
		}
		setModel(listModel);
	}

}
