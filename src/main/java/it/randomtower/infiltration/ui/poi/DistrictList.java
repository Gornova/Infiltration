package it.randomtower.infiltration.ui.poi;

import java.io.IOException;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.model.poi.Poi;

@SuppressWarnings("serial")
public class DistrictList extends JList {

	public DistrictList() throws IOException {
		setCellRenderer(new DistrictListRenderer());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object elem = getSelectedValue();
					if (elem != null) {
						// try {
						// actorPanel.setCurrentPoi(currentPoi);
						// actorPanel.setModel((BaseActor) elem);
						// } catch (IOException e1) {
						// e1.printStackTrace();
						// }
						// actorPanel.setVisible(true);
					}
				}
			}
		});
	}

	public void setCurrentModel(Poi poi) {
		DefaultListModel<District> listModel = new DefaultListModel<>();
		Collections.sort(poi.getDistricts());
		for (District ditrict : poi.getDistricts()) {
			listModel.addElement(ditrict);
		}
		setModel(listModel);
	}

}
