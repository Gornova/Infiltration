package it.randomtower.infiltration.ui.actor;

import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.Relationship;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.dto.RelationDto;

@SuppressWarnings("serial")
public class RelationList extends JList {

	private int mHoveredJListIndex = -1;
	private ActorPanel actorPanel;
	private Actor current;

	public RelationList(ActorPanel actorPanel, Scenario scenario) {
		this.actorPanel = actorPanel;
		setCellRenderer(new RelationListRenderer());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object elem = getSelectedValue();
					if (elem != null) {
						RelationDto dto = (RelationDto) elem;
						// MapPanel.appendLog(
						// "Manipulation on " + dto.actor.getName() + " relation
						// of " + dto.relation.toString());
						// scenario.act(ActionEnum.MANIPULATE, current,
						// dto.actor, actorPanel.getCurrentPoi());
						actorPanel.getMapPanel().displayChallengePanel(
								new Challenge(ActionEnum.MANIPULATE, current, dto.actor, actorPanel.getCurrentPoi()));
					}
				}
			}
		});
	}

	public void setCurrentModel(Actor model) {
		this.current = model;
		DefaultListModel<RelationDto> listModel = new DefaultListModel<>();
		for (Entry<Actor, Relationship> entry : model.getRelations()) {
			RelationDto dto = new RelationDto();
			dto.actor = entry.getKey();
			dto.relation = entry.getValue();
			listModel.addElement(dto);
		}
		setModel(listModel);

	}

}
