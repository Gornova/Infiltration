package it.randomtower.infiltration.ui.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class MoveActionPanel extends JPanel {

	// TODO: to common class with other panels ?
	// private Color color = new Color(74, 80, 80);
	private Color color = new Color(124, 126, 126);
	private JLabel lblTitle;
	private MapPanel mapPanel;
	private JButton btnCancel;
	private JButton btnContinue;
	private Actor actor;
	private BasePoi poi;
	private JList list;

	public MoveActionPanel(MapPanel mapPanel) throws IOException {
		this.mapPanel = mapPanel;
		setBounds(256, 128, 255, 400);
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);

		lblTitle = new JLabel("Select destination");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(20, 17, 151, 14);
		add(lblTitle);

		btnContinue = new JButton("Confirm");
		btnContinue.setBounds(82, 366, 89, 23);
		btnContinue.setBackground(color);
		btnContinue.setBorder(new LineBorder(Color.black));
		btnContinue.setEnabled(false);
		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {

				}
				mapPanel.refresh();
			}
		});
		add(btnContinue);

		btnCancel = new JButton("Close");
		btnCancel.setBounds(149, 13, 78, 23);
		btnCancel.setBackground(color);
		btnCancel.setBorder(new LineBorder(Color.black));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(btnCancel);

		list = new JList();
		list.setBounds(20, 42, 207, 310);
		list.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		list.setLayout(new BorderLayout(0, 0));
		list.setOpaque(false);
		list.setForeground(color);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					Object elem = list.getSelectedValue();
					if (elem != null) {
						list.setEnabled(true);
						// move from current poi to target poi
						BasePoi from = poi;
						BasePoi target = Scenario.getInstance().findPoi(elem.toString());
						Scenario.getInstance().addAnimation(actor, from, target);
						setVisible(false);
					}
				}
			}
		});
		add(list);
	}

	public void refresh() {
	}

	public void setActorAndPoi(Actor actor, BasePoi poi) {
		this.actor = actor;
		this.poi = poi;
		DefaultListModel<String> listModel = new DefaultListModel<>();
		mapPanel.getScenario().getPois().stream().filter(p -> !p.isHidden())
				.filter(p -> !p.getName().equals(poi.getName())).forEach(p -> listModel.addElement(p.getName()));
		list.setModel(listModel);
	}
}
