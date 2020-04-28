package it.randomtower.infiltration.ui.actor;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.ActorRole;
import it.randomtower.infiltration.model.actor.tag.Tag;
import it.randomtower.infiltration.model.faction.Faction;
import it.randomtower.infiltration.model.faction.FactionStatusEnum;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.action.CallingActionButton;
import it.randomtower.infiltration.ui.action.CorruptActionButton;
import it.randomtower.infiltration.ui.action.MoveActorActionButton;
import it.randomtower.infiltration.ui.map.MapPanel;
import it.randomtower.infiltration.ui.poi.PoiPanel;

@SuppressWarnings("serial")
public class ActorPanel extends JPanel {

	private JLabel nameLabel;
	private Actor model;
	private JLabel actorRoleLabel;
	private JTextArea descriptionLabel;
	private JLabel attackLabel;
	private JLabel healthLabel;
	private JLabel madnessLabel;
	private JLabel arcaneLabel;
	private JLabel moneyLabel;
	private CorruptActionButton corruptButton;
	private Scenario scenario;
	private JLabel factionLabel;
	private JLabel lblRelationships;
	private RelationList relationList;
	private CallingActionButton callingButton;
	private PoiPanel poiPanel;
	private Poi currentPoi;
	private JLabel lblCurrentactionlabel;
	private PortraitButton portraitButton;
	private JLabel moneyBonusLabel;
	private JLabel followersLabel;
	private JLabel followersBonusLabel;
	private JLabel influenceLabel;
	private QuestButton questButton;
	private JLabel tagLabel;
	private MapPanel mapPanel;
	private MoveActorActionButton moveActionButton;

	public ActorPanel(Scenario scenario, MapPanel mapPanel, PoiPanel poiPanel) throws IOException {
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.scenario = scenario;
		this.mapPanel = mapPanel;
		setBounds(new Rectangle(0, 0, 256, 768));
		setBackground(new Color(124, 126, 126));
		setLayout(null);

		this.poiPanel = poiPanel;

		nameLabel = new JLabel("nameLabel");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(10, 36, 76, 14);
		add(nameLabel);

		actorRoleLabel = new JLabel("actorRole");
		actorRoleLabel.setForeground(Color.WHITE);
		actorRoleLabel.setEnabled(false);
		actorRoleLabel.setBounds(10, 11, 101, 14);
		add(actorRoleLabel);

		descriptionLabel = new JTextArea("");
		descriptionLabel.setWrapStyleWord(true);
		descriptionLabel.setLineWrap(true);
		descriptionLabel.setEditable(false);
		descriptionLabel.setForeground(Color.WHITE);
		descriptionLabel.setBackground(new Color(124, 126, 126));
		descriptionLabel.setBounds(10, 113, 236, 42);
		add(descriptionLabel);

		// TODO: icons for characters statistics
		attackLabel = new JLabel("attackLabel");
		attackLabel.setForeground(Color.WHITE);
		attackLabel.setBounds(10, 189, 119, 14);
		attackLabel.setToolTipText("Attack strenght of this characters");
		add(attackLabel);

		healthLabel = new JLabel("healthLabel");
		healthLabel.setForeground(Color.WHITE);
		healthLabel.setBounds(127, 189, 119, 14);
		healthLabel.setToolTipText("Vitality measure of how many attacks this character can handle");
		add(healthLabel);

		madnessLabel = new JLabel("madnessLabel");
		madnessLabel.setForeground(Color.WHITE);
		madnessLabel.setBounds(127, 214, 119, 14);
		madnessLabel.setToolTipText("Madness track how differently this character will act\n and responses to events ");
		add(madnessLabel);

		arcaneLabel = new JLabel("arcaneLabel");
		arcaneLabel.setForeground(Color.WHITE);
		arcaneLabel.setBounds(10, 214, 119, 14);
		arcaneLabel.setToolTipText("Arcane measure how much knowledge of arcane powers \nthis characters has");
		add(arcaneLabel);

		moneyLabel = new JLabel("moneyLabel");
		moneyLabel.setForeground(Color.WHITE);
		moneyLabel.setBounds(10, 239, 119, 14);
		moneyLabel.setToolTipText("Money represent how much resources and influence \ncharacter has in the society ");
		add(moneyLabel);

		moneyBonusLabel = new JLabel("moneyBonusLabel");
		moneyBonusLabel.setForeground(Color.WHITE);
		moneyBonusLabel.setBounds(127, 237, 119, 14);
		moneyBonusLabel.setToolTipText("Money bonus every turn");
		// add(moneyBonusLabel);

		// TODO: move this as parameter
		BufferedImage corruptImage = Main.getFileResource("Icon.5_05.jpg");
		BufferedImage corruptImageRoll = Main.getFileResource("Icon.5_05.jpg");
		corruptButton = new CorruptActionButton(corruptImage, corruptImageRoll, 50, 50, scenario, mapPanel);
		corruptButton.setLocation(10, 70);
		corruptButton.setSize(32, 32);
		add(corruptButton);

		BufferedImage callingImage = Main.getFileResource("Icons8_30.jpg");
		BufferedImage callingImageRoll = Main.getFileResource("Icons8_30.jpg");
		callingButton = new CallingActionButton(callingImage, callingImageRoll, 50, 50, scenario, mapPanel);
		callingButton.setEnabled(false);
		callingButton.setLocation(52, 70);
		callingButton.setSize(32, 32);
		add(callingButton);

		BufferedImage questImage = Main.getFileResource("quest.png");
		BufferedImage questImageRoll = Main.getFileResource("quest_roll.png");
		questButton = new QuestButton(questImage, questImageRoll, 74, 27, scenario, mapPanel);
		questButton.setToolTipText("quest log");
		questButton.setLocation(120, 11);
		questButton.setEnabled(true);
		questButton.setVisible(false);
		questButton.setSize(19, 21);
		add(questButton);

		factionLabel = new JLabel("FactionLabel");
		factionLabel.setBounds(10, 314, 236, 14);
		factionLabel.setForeground(Color.WHITE);
		add(factionLabel);

		tagLabel = new JLabel("tagLabel");
		tagLabel.setBounds(10, 339, 236, 14);
		tagLabel.setForeground(Color.WHITE);
		add(tagLabel);

		lblRelationships = new JLabel("Relationships");
		lblRelationships.setForeground(Color.WHITE);
		lblRelationships.setBounds(10, 357, 236, 14);
		add(lblRelationships);

		relationList = new RelationList(this, scenario);
		relationList.setBounds(10, 382, 236, 209);
		relationList.setBackground(new Color(74, 80, 80));
		relationList.setForeground(Color.white);

		add(relationList);

		lblCurrentactionlabel = new JLabel("currentActionLabel");
		lblCurrentactionlabel.setForeground(Color.WHITE);
		lblCurrentactionlabel.setBounds(10, 155, 236, 14);
		add(lblCurrentactionlabel);

		portraitButton = new PortraitButton(40, 50);
		portraitButton.setBounds(148, 11, 90, 90);
		add(portraitButton);

		followersLabel = new JLabel("followersLabel");
		followersLabel.setToolTipText("Number of followers, used for solving quests in the city");
		followersLabel.setForeground(Color.WHITE);
		followersLabel.setBounds(127, 239, 119, 14);
		add(followersLabel);

		followersBonusLabel = new JLabel("followersBonusLabel");
		followersBonusLabel.setToolTipText("Bonus for followers every turn");
		followersBonusLabel.setForeground(Color.WHITE);
		followersBonusLabel.setBounds(127, 264, 119, 14);

		influenceLabel = new JLabel("influenceLabel");
		influenceLabel.setToolTipText(
				"Character influence in this poi\nInfluence are used to gain access to more high level quests and structure in the poi");
		influenceLabel.setForeground(Color.WHITE);
		influenceLabel.setBounds(10, 264, 119, 14);
		add(influenceLabel);

		BufferedImage moveImage = Main.getFileResource("Icon.4_81.jpg");
		BufferedImage moveImageRoll = Main.getFileResource("Icon.4_81.jpg");
		moveActionButton = new MoveActorActionButton(moveImage, moveImageRoll, 50, 50, scenario, mapPanel);
		moveActionButton.setEnabled(false);
		moveActionButton.setVisible(true);
		moveActionButton.setBounds(94, 70, 32, 32);
		add(moveActionButton);

		// TODO: move to dedicated panel?
		visibleStatistics(false);
	}

	private BufferedImage getFromRole(Actor model) throws IOException {
		String fileName = "";
		switch (model.getRole()) {
		case WARRIOR:
			fileName = "portraits/warrior/TCP Armored 1.jpg";
			break;
		case ASSASSIN:
			fileName = "portraits/assassin/TCP Pirate 2.jpg";
			break;
		case MAGE:
			fileName = "portraits/mage/TCP Scary 1.jpg";
			break;
		case MERCHANT:
			fileName = "portraits/merchant/TCP Cyberpunk 5.jpg";
			break;
		case THIEF:
			fileName = "portraits/thief/TCP Human 6.jpg";
			break;
		default:
			fileName = "portraits/warrior/TCP Armored 1.jpg";
			break;
		}
		return Main.getFileResource(fileName);
	}

	public void setModel(Actor model) throws IOException {
		this.model = model;
		corruptButton.setModel(model, poiPanel.getPoi());
		callingButton.setModel(model, poiPanel.getPoi());
		moveActionButton.setModel(model, poiPanel.getPoi());
		questButton.setModel(model, poiPanel.getPoi());
		actorRoleLabel.setText(model.getRole().toString());
		nameLabel.setText(model.getName());
		lblCurrentactionlabel.setText("Current action: " + scenario.getCurrentAction(model));

		if (getCurrentPoi().getStatus() != null) {
			if (scenario.getAncient().getPower() == 0) {
				enableAction(false);
			} else {
				enableAction(true);
				switch (getCurrentPoi().getStatus()) {
				case PEACE:
					displayNormalStatistics(model);
					break;
				case REBELLION:
					displayRebellionStatistics(model);
					break;
				default:
					break;
				}
			}
		}
		if (scenario.getCurrentAction(model) == ActionEnum.INVESTIGATE) {
			enableAction(false);
		}
		if (model.getFaction() != null && model.getFaction().getStatus() == FactionStatusEnum.WAR) {
			enableAction(false);
		}

		if (!model.getRole().equals(ActorRole.MONSTER)) {
			// set portrait
			BufferedImage portTraitImage = getFromRole(model);
			Image scaled = portTraitImage.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
			portraitButton.setIcon(scaled);
			portraitButton.setVisible(true);
			showAction(true);
		} else {
			portraitButton.setVisible(false);
			descriptionLabel.setText(model.getDescription());
			descriptionLabel.setVisible(true);
			showAction(false);
		}
	}

	private void displayNormalStatistics(Actor model) {
		if (model.isCorrupted()) {
			corruptButton.setEnabled(!model.isCorrupted());
			callingButton.setEnabled(true);
			descriptionLabel.setText(model.getDescription());
			updateStats(model);

			factionLabel.setText("Faction: " + model.getFaction().getName());
			String factionTooltip = "Faction: " + model.getFaction().getName();
			if (model.getFaction().getStatus() != null) {
				factionTooltip += "\nCurrent status " + model.getFaction().getStatus().toString();
				factionTooltip += "\nRelations:\n\n" + getRelationTooltip(model.getFaction());
				if (model.getFaction().getStatus() == FactionStatusEnum.WAR) {
					factionTooltip += "\nAt war with " + model.getFaction().getTarget();
				}
			}
			factionLabel.setToolTipText(factionTooltip);

			List<Tag> tags = model.getTags();
			if (tags != null && !tags.isEmpty()) {
				String text = "Tags : ";
				String tooltip = "";
				for (Tag tag : tags) {
					if (tag != null) {
						text += " " + tag.getName();
						tooltip += " " + tag.getDescription() + "\n";
					}
				}
				tagLabel.setText(text);
				tagLabel.setToolTipText(tooltip);
			} else {
				tagLabel.setText("");
				tagLabel.setToolTipText("");
			}

			relationList.setCurrentModel(model);
			visibleStatistics(true);
		} else {
			callingButton.setEnabled(false);
			visibleStatistics(false);
		}
	}

	private void updateStats(Actor model) {
		attackLabel.setText("Attack: " + model.getAttack() + " / 10");
		healthLabel.setText("Health: " + model.getHealth() + " / 10");
		madnessLabel.setText("Madness: " + model.getMadness() + " / 10");
		arcaneLabel.setText("Arcane: " + model.getArcane() + " / 10");
		moneyLabel.setText("Money: " + model.getMoney() + " / 10");
		moneyBonusLabel.setText("bonus " + model.getMoneyBonus());
		followersLabel.setText("Followers: " + model.getFollowers() + " / 10");
		followersBonusLabel.setText("bonus " + model.getFollowersBonus());
		influenceLabel.setText("Influence: " + model.getInfluence() + " / 10");
	}

	private String getRelationTooltip(Faction faction) {
		String result = "";
		for (String name : faction.getRelations().keySet()) {
			if (!name.equals(faction.getName())) {
				result += name + " " + faction.getRelations().get(name) + "\n";
			}
		}
		return result;
	}

	private void displayRebellionStatistics(Actor model) {
		if (model.isCorrupted()) {
			corruptButton.setEnabled(!model.isCorrupted());
			descriptionLabel.setText(model.getDescription());
			updateStats(model);
			factionLabel.setText("Faction: " + model.getFaction().getName());
			factionLabel.setToolTipText(model.getFaction().getDescription());
			relationList.setCurrentModel(model);
			visibleStatistics(true);
		} else {
			visibleStatistics(false);
		}
//		callingButton.setEnabled(false);
	}

	public void refresh() throws IOException {
		if (model != null && !model.isDead()) {
			setModel(model);

			if (getCurrentPoi() != null && getCurrentPoi().getStatus() != null) {
				switch (getCurrentPoi().getStatus()) {
				case PEACE:
					break;
				case REBELLION:
//					callingButton.setEnabled(false);
					break;
				}
			}
		} else if (model != null && model.isDead()) {
			enableAction(false);
		}
	}

	private void enableAction(boolean b) {
		corruptButton.setEnabled(b);
		callingButton.setEnabled(b);
		relationList.setEnabled(b);
	}

	private void showAction(boolean b) {
		corruptButton.setVisible(b);
		callingButton.setVisible(b);
		moveActionButton.setVisible(b);
		relationList.setVisible(b);
	}

	private void visibleStatistics(boolean b) {
		lblCurrentactionlabel.setVisible(b);
		actorRoleLabel.setVisible(b);
		descriptionLabel.setVisible(b);
		attackLabel.setVisible(b);
		healthLabel.setVisible(b);
		madnessLabel.setVisible(b);
		arcaneLabel.setVisible(b);
		moneyLabel.setVisible(b);
		moneyBonusLabel.setVisible(b);
		followersLabel.setVisible(b);
		followersBonusLabel.setVisible(b);
		influenceLabel.setVisible(b);
		factionLabel.setVisible(b);
		lblRelationships.setVisible(b);
		relationList.setVisible(b);
		questButton.setVisible(b);
		tagLabel.setVisible(b);
	}

	public void setCurrentPoi(Poi currentPoi) {
		this.currentPoi = currentPoi;
	}

	public Poi getCurrentPoi() {
		return currentPoi;
	}

	public MapPanel getMapPanel() {
		return mapPanel;
	}
}
