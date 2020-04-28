package it.randomtower.infiltration.ui.poi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.ui.actor.ActorPanel;

@SuppressWarnings("serial")
public class PoiPanel extends JPanel {
	private JLabel poiTypeLabel;
	private JLabel poiNameLabel;
	private JTextArea poiDescriptionLabel;
	private JLabel poiStatusLabel;
	private ActorList actorList;
	private JLabel notableCharLabel;
	private ActorPanel actorPanel;
	private FoodButton foodButton;
	private JLabel foodValueLabel;
	// private FoodButton workButton;
	// private JLabel workValueLabel;
	private FoodButton moneyButton;
	private JLabel moneyValueLabel;
	private Poi poi;
	private FoodButton rebelButton;
	private JLabel rebelValueLabel;
	private FoodButton popButton;
	private JLabel popValueLabel;
	private JLabel lblDistricts;
	private DistrictList districtList;
	private FoodButton cluesButton;
	private DistrictPanel districtPanel;
	private JScrollPane actorScrollPane;
	private BufferedImage cluesImage;

	public PoiPanel() throws IOException {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setBounds(new Rectangle(0, 0, 256, 768));
		setBackground(new Color(74, 80, 80));
		setLayout(null);

		poiTypeLabel = new JLabel("poiType");
		poiTypeLabel.setEnabled(false);
		poiTypeLabel.setForeground(Color.white);
		poiTypeLabel.setBounds(10, 11, 119, 14);
		add(poiTypeLabel);

		poiNameLabel = new JLabel("poiName");
		poiNameLabel.setToolTipText("name of selected point of interest");
		poiNameLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		poiNameLabel.setForeground(Color.white);
		poiNameLabel.setBounds(10, 36, 119, 14);
		add(poiNameLabel);

		poiDescriptionLabel = new JTextArea("");
		poiDescriptionLabel.setBounds(10, 55, 236, 62);
		poiDescriptionLabel.setBackground(new Color(74, 80, 80));
		poiDescriptionLabel.setForeground(Color.white);
		poiDescriptionLabel.setLineWrap(true);
		poiDescriptionLabel.setEditable(false);
		poiDescriptionLabel.setWrapStyleWord(true);
		add(poiDescriptionLabel);

		poiStatusLabel = new JLabel("poiStatus");
		poiStatusLabel.setToolTipText("status for selected point of interest");
		poiStatusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		poiStatusLabel.setForeground(Color.WHITE);
		poiStatusLabel.setBounds(127, 36, 108, 14);
		add(poiStatusLabel);

		actorList = new ActorList();
		actorList.setBounds(10, 254, 236, 200);
		actorList.setBackground(new Color(74, 80, 80));
		actorList.setForeground(Color.white);

		actorScrollPane = new JScrollPane(actorList);
		actorScrollPane.setBounds(10, 214, 236, 200);
		add(actorScrollPane);

		districtList = new DistrictList();
		districtList.setBounds(10, 513, 236, 115);
		districtList.setBackground(new Color(74, 80, 80));
		districtList.setForeground(Color.white);
		// add(districtList);

		districtPanel = new DistrictPanel();
		districtPanel.setBounds(10, 450, 236, 275);
		districtPanel.setBackground(new Color(74, 80, 80));
		districtPanel.setForeground(Color.white);
		add(districtPanel);

		notableCharLabel = new JLabel("Notable characters");
		notableCharLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		notableCharLabel.setForeground(Color.WHITE);
		notableCharLabel.setBounds(10, 229, 236, 14);

		add(notableCharLabel);

		// TODO: move this as parameter
		BufferedImage foodImage = Main.getFileResource("Icon.2_15.jpg");
		foodButton = new FoodButton(foodImage, foodImage, 50, 50,
				"Measure of foodstuffs\nFood is correlated to population of this poi (from 0 to 10)\nAt zero a famine starts");
		foodButton.setLocation(10, 171);
		foodButton.setSize(32, 32);
		add(foodButton);

		foodValueLabel = new JLabel("foodValue");
		foodValueLabel.setForeground(Color.WHITE);
		foodValueLabel.setBounds(52, 171, 46, 32);
		add(foodValueLabel);

		// TODO: move this as parameter
		BufferedImage moneyImage = Main.getFileResource("Icons8_78.jpg");
		moneyButton = new FoodButton(moneyImage, moneyImage, 50, 50, "Money\nWealth of this poi");
		moneyButton.setLocation(97, 171);
		moneyButton.setSize(32, 32);
		add(moneyButton);

		moneyValueLabel = new JLabel("moneyValue");
		moneyValueLabel.setForeground(Color.WHITE);
		moneyValueLabel.setBounds(138, 171, 46, 32);
		add(moneyValueLabel);

		BufferedImage rebelImage = Main.getFileResource("Icon.6_68.jpg");
		rebelButton = new FoodButton(rebelImage, rebelImage, 70, 70,
				"Rebellion\nChance of a revolt start (from 0 - 10)\nAt ten a rebellion starts");
		rebelButton.setLocation(10, 128);
		rebelButton.setSize(32, 32);
		add(rebelButton);

		rebelValueLabel = new JLabel("rebelValue");
		rebelValueLabel.setForeground(Color.WHITE);
		rebelValueLabel.setBounds(52, 128, 46, 32);
		add(rebelValueLabel);

		BufferedImage popImage = Main.getFileResource("Icon.6_01.jpg");
		popButton = new FoodButton(popImage, popImage, 70, 70,
				"Population\nMeasurement of people in this poi\nWhen a war or rebellion, for every 1000 of population, an unit is created");
		popButton.setLocation(97, 128);
		popButton.setSize(32, 32);
		add(popButton);

		popValueLabel = new JLabel("popValue");
		popValueLabel.setForeground(Color.WHITE);
		popValueLabel.setBounds(139, 128, 46, 32);
		add(popValueLabel);

		lblDistricts = new JLabel("Districts");
		lblDistricts.setToolTipText(
				"Display list of city's districts\r\nEvery district can be conquered by a character \r\nand this will result in more bonuses");
		lblDistricts.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDistricts.setForeground(Color.WHITE);
		lblDistricts.setBounds(10, 425, 236, 14);
		add(lblDistricts);

		cluesImage = Main.getFileResource("Icon.5_24_colored.jpg");
		cluesButton = new FoodButton((BufferedImage) cluesImage, (BufferedImage) cluesImage, 70, 70,
				"Ancient secrets\nAn ancient secret is hidden here");
		cluesButton.setBounds(203, 150, 32, 32);
		cluesButton.setVisible(false);
		add(cluesButton);
	}

	public void setPoi(Poi poi) {
		poiTypeLabel.setText(poi.getType().toString());
		poiNameLabel.setText(poi.getName());
		poiDescriptionLabel.setText(poi.getDescription());
		if (poi.getStatus() != null) {
			poiStatusLabel.setText(poi.getStatus().toString());
		}
		foodValueLabel.setText("" + poi.getFood());
		// workValueLabel.setText("" + poi.getWork());
		moneyValueLabel.setText("" + poi.getMoney());
		rebelValueLabel.setText("" + poi.getRebellion());
		popValueLabel.setText("" + poi.getPopulation());
		this.poi = poi;
		districtPanel.refresh(poi.getDistricts());

		if (poi.getType().equals(PoiType.RUIN)) {
			lblDistricts.setVisible(false);
			districtPanel.setVisible(false);
			districtList.setVisible(false);
			foodValueLabel.setVisible(false);
			foodButton.setVisible(false);
			moneyValueLabel.setVisible(false);
			moneyButton.setVisible(false);
			rebelValueLabel.setVisible(false);
			rebelButton.setVisible(false);
			popValueLabel.setVisible(false);
			popButton.setVisible(false);
			cluesButton.setVisible(false);
			poiStatusLabel.setVisible(false);
			if (poi.getActors().stream().anyMatch(a -> a.isCorrupted())) {
				actorList.setCurrentModel(poi.getActors(), poi);
//				actorList.setCurrentModel(poi.getActors().stream()
//						.filter(p -> !p.getFaction().getName().equals(FactionBuilder.MONSTER_FACTION))
//						.collect(Collectors.toList()), poi);
				// show something if a currupted agent is here
				actorList.setVisible(true);
				actorScrollPane.setVisible(true);
				notableCharLabel.setVisible(true);
				cluesButton.setVisible(true);
//				districtPanel.setVisible(true);
//				districtList.setVisible(true);
			} else {
				actorList.setVisible(false);
				actorScrollPane.setVisible(false);
				notableCharLabel.setVisible(false);
			}
		} else {
			actorList.setCurrentModel(poi.getActors(), poi);
			districtList.setCurrentModel(poi);

			lblDistricts.setVisible(true);
			districtPanel.setVisible(true);
			districtList.setVisible(true);
			foodValueLabel.setVisible(true);
			foodButton.setVisible(true);
			moneyValueLabel.setVisible(true);
			moneyButton.setVisible(true);
			rebelValueLabel.setVisible(true);
			rebelButton.setVisible(true);
			popValueLabel.setVisible(true);
			popButton.setVisible(true);
			cluesButton.setVisible(true);
			poiStatusLabel.setVisible(true);
			actorList.setVisible(true);
			actorScrollPane.setVisible(true);
			notableCharLabel.setVisible(true);
		}
		cluesButton.setVisible(!poi.getSecrets().isEmpty());

	}

	public void setActorPanel(ActorPanel actorPanel) {
		this.actorPanel = actorPanel;
		actorList.setActorPanel(actorPanel);
	}

	public ActorPanel getActorPanel() {
		return actorPanel;
	}

	public void refresh() {
		if (poi != null) {
			setPoi(poi);
		}
	}

	public Poi getPoi() {
		return poi;
	}
}
