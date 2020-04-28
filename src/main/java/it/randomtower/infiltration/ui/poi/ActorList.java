package it.randomtower.infiltration.ui.poi;

import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.ui.actor.ActorPanel;

@SuppressWarnings("serial")
public class ActorList extends JList {

	private ActorPanel actorPanel;
	private Poi currentPoi;

	public ActorList() {
		setCellRenderer(new ActorListRenderer());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object elem = getSelectedValue();
					if (elem != null) {
						try {
							actorPanel.setCurrentPoi(currentPoi);
							actorPanel.setModel((Actor) elem);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						actorPanel.setVisible(true);
					}
				}
			}
		});
	}

	public void setCurrentModel(List<Actor> actors, Poi poi) {
		this.currentPoi = poi;
		DefaultListModel<Actor> listModel = new DefaultListModel<>();
		for (Actor actor : actors) {
			listModel.addElement(actor);
		}
		setModel(listModel);
	}

	public void setActorPanel(ActorPanel actorPanel) {
		this.actorPanel = actorPanel;
	}

}
