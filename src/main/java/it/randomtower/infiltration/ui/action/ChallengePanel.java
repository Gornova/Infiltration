package it.randomtower.infiltration.ui.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import it.randomtower.infiltration.ChallengeType;
import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.tag.Tag;
import it.randomtower.infiltration.model.actor.tag.TagBuilder;
import it.randomtower.infiltration.model.actor.tag.TagEffect;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.model.faction.FactionStatusEnum;
import it.randomtower.infiltration.model.poi.StatusEnum;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class ChallengePanel extends JPanel {

	// TODO: to common class with other panels ?
	// private Color color = new Color(74, 80, 80);
	private Color color = new Color(124, 126, 126);
	private Challenge challenge;
	private JLabel lblTitle;
	private MapPanel mapPanel;
	private ChallengeModifierList challengeBonusList;
	private JLabel lblTotalattack;
	private JPanel bonusPanel;
	private JPanel malusPanel;
	private ChallengeModifierList challengeMalusList;
	private JLabel lblTotaldefense;
	private float attack;
	private float defense;
	private Random rnd = new Random();
	private JButton btnCancel;
	private JLabel lblchallengeResult;
	private JButton btnContinue;
	private ActionEnum playerAction;

	public ChallengePanel(MapPanel mapPanel) throws IOException {
		this.mapPanel = mapPanel;
		setBounds(256, 128, 512, 400);
		setBackground(color);
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);

		lblTitle = new JLabel("Challenge");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBounds(198, 11, 151, 14);
		add(lblTitle);

		bonusPanel = new JPanel();
		bonusPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bonusPanel.setBounds(20, 48, 220, 298);
		bonusPanel.setBackground(color);
		bonusPanel.setLayout(null);
		add(bonusPanel);

		JLabel lblAttack = new JLabel("Attack");
		lblAttack.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAttack.setBounds(10, 11, 67, 14);
		lblAttack.setForeground(Color.WHITE);

		bonusPanel.add(lblAttack);

		challengeBonusList = new ChallengeModifierList();
		challengeBonusList.setBounds(10, 36, 200, 251);
		challengeBonusList.setBackground(new Color(74, 80, 80));
		challengeBonusList.setForeground(Color.white);
		bonusPanel.add(challengeBonusList);

		lblTotalattack = new JLabel("TotalAttack");
		lblTotalattack.setBounds(150, 11, 60, 14);
		bonusPanel.add(lblTotalattack);

		malusPanel = new JPanel();
		malusPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		malusPanel.setBounds(270, 48, 220, 298);
		malusPanel.setBackground(color);
		malusPanel.setLayout(null);

		add(malusPanel);

		challengeMalusList = new ChallengeModifierList();
		challengeMalusList.setBounds(10, 36, 200, 251);
		challengeMalusList.setBackground(new Color(74, 80, 80));
		challengeMalusList.setForeground(Color.white);
		malusPanel.add(challengeMalusList);

		JLabel lblDefense = new JLabel("Defense");
		lblDefense.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDefense.setBounds(161, 11, 49, 14);
		lblDefense.setForeground(Color.WHITE);

		malusPanel.add(lblDefense);

		lblTotaldefense = new JLabel("TotalDefense");
		lblTotaldefense.setBounds(10, 11, 67, 14);
		malusPanel.add(lblTotaldefense);

		btnContinue = new JButton("Continue");
		btnContinue.setBounds(20, 366, 89, 23);
		btnContinue.setBackground(color);
		btnContinue.setBorder(new LineBorder(Color.black));
		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("continue with challenge, attack = " +
				// attack + " , defense =" + defense);
				float roll = rnd.nextFloat();
				if (roll + attack >= defense) {
					MapPanel.appendLog(challenge.getPoi().getName(), "Mental challenge won, started corrupt on "
							+ challenge.getFrom().getName() + " in " + challenge.getPoi().getName());
					mapPanel.getScenario().act(playerAction, challenge.getFrom(), challenge.getTo(),
							challenge.getPoi());
					lblchallengeResult.setText("Challenge completed");
				} else {
					lblchallengeResult.setText("Challenge not completed!");
				}
				btnContinue.setVisible(false);
				lblchallengeResult.setVisible(true);

				mapPanel.refresh();
			}
		});
		add(btnContinue);

		btnCancel = new JButton("Close");
		btnCancel.setBounds(401, 366, 89, 23);
		btnCancel.setBackground(color);
		btnCancel.setBorder(new LineBorder(Color.black));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(btnCancel);

		lblchallengeResult = new JLabel("ChallengeResult");
		lblchallengeResult.setBounds(175, 370, 155, 14);
		lblchallengeResult.setForeground(Color.WHITE);
		add(lblchallengeResult);
	}

	public void setChallenge(Challenge challenge) {
		this.challenge = challenge;
		lblTitle.setText("Challenge " + challenge.getAction().toString());
		switch (challenge.getAction()) {
		case CORRUPT:
			setupActionCorrupt();
			playerAction = ActionEnum.CORRUPT;
			break;
		case MANIPULATE:
			setupManipulate();
			playerAction = ActionEnum.MANIPULATE;
			break;
		case CALLING:
			setupCalling();
			playerAction = ActionEnum.CALLING;
			break;
		default:
			break;
		}
		btnContinue.setVisible(true);
		btnCancel.setVisible(true);

		lblchallengeResult.setVisible(false);
	}

	// calling can be a physical / mental / social
	private void setupCalling() {
		List<Tag> attackTags = new ArrayList<>();
		attack = 0;
		// calling action are influenced by how much powerful is the old one
		// bonus for player: +20% for every ancient power
		attackTags.add(TagBuilder.buildAncientPower(mapPanel.getScenario().getAncient().getPower(),
				TagEffect.MENTAL_BONUS, ChallengeType.MENTAL));
		if (challenge.getPoi().getStatus().equals(StatusEnum.REBELLION)) {
			attackTags.add(new Tag("rebellion", "rebellion and disorder weaken mortal minds\n+30% on ancient actions",
					TagEffect.SOCIAL_BONUS, true, ChallengeType.PHYSICAL, 0.3f));
		}
		for (Tag tag : attackTags) {
			attack += tag.getValue();
		}
		attack = Math.min(1, Math.max(0, attack));

		challengeBonusList.setCurrentModel(attackTags);
		lblTotalattack.setText("" + Math.rint(attack * 100) + " %");

		// defense in this case is how much powerful is target: if he/she has
		// mental bonus/malus trait
		Actor defender = challenge.getFrom();
		List<Tag> defenseTag = getDefenseValue(defender, ChallengeType.ANY);
		defense = 0;
		Tag factionModifier = getFactionModifiers(defender);
		if (factionModifier != null) {
			defenseTag.add(factionModifier);
			Tag rebellionTag = new Tag("guild war", "humans thath fight for no reason\n-20% on defense",
					TagEffect.PHYSICAL_MALUS, false, ChallengeType.PHYSICAL, -0.2f);
			// if guild is at war, is a malus for the defender -20%
			defenseTag.add(rebellionTag);
		}
		for (Tag tag : defenseTag) {
			defense += tag.getValue();
		}
		defense = Math.min(1, Math.max(0, defense));

		challengeMalusList.setCurrentModel(defenseTag);

		lblTotaldefense.setText("" + Math.rint(defense * 100) + " %");
	}

	// manipulate is a social challenge
	private void setupManipulate() {
		List<Tag> attackTags = new ArrayList<>();
		attack = 0;
		// manipulate action are influenced by how much powerful is the old one
		// bonus for player: +20% for every ancient power
		attackTags.add(TagBuilder.buildAncientPower(mapPanel.getScenario().getAncient().getPower(),
				TagEffect.SOCIAL_BONUS, ChallengeType.SOCIAL));
		if (challenge.getPoi().getStatus().equals(StatusEnum.REBELLION)) {
			attackTags.add(new Tag("rebellion", "rebellion and disorder weaken mortal minds\n+30% on ancient actions",
					TagEffect.SOCIAL_BONUS, true, ChallengeType.SOCIAL, 0.3f));
		}
		for (Tag tag : attackTags) {
			attack += tag.getValue();
		}
		attack = Math.min(1, Math.max(0, attack));

		challengeBonusList.setCurrentModel(attackTags);
		lblTotalattack.setText("" + Math.rint(attack * 100) + " %");

		// defense in this case is how much powerful is target: if he/she has
		// mental bonus/malus trait
		Actor defender = challenge.getFrom();
		List<Tag> defenseTag = getDefenseValue(defender, ChallengeType.SOCIAL);
		defense = 0;
		Tag factionModifier = getFactionModifiers(defender);
		if (factionModifier != null) {
			defenseTag.add(factionModifier);
			Tag rebellionTag = new Tag("guild war", "humans thath fight for no reason\n-20% on defense",
					TagEffect.SOCIAL_MALUS, false, ChallengeType.SOCIAL, -0.2f);
			// if guild is at war, is a malus for the defender -20%
			defenseTag.add(rebellionTag);
		}
		for (Tag tag : defenseTag) {
			defense += tag.getValue();
		}
		defense = Math.min(1, Math.max(0, defense));

		challengeMalusList.setCurrentModel(defenseTag);

		lblTotaldefense.setText("" + Math.rint(defense * 100) + " %");

	}

	// corrupt is a mental challenge
	private void setupActionCorrupt() {
		List<Tag> attackTags = new ArrayList<>();

		// corrupt action are influenced by how much powerful is the old one
		// bonus for player: +20% for every ancient power
		attack = 0;
		attackTags.add(TagBuilder.buildAncientPower(mapPanel.getScenario().getAncient().getPower(),
				TagEffect.MENTAL_BONUS, ChallengeType.MENTAL));

		if (challenge.getPoi().getStatus().equals(StatusEnum.REBELLION)) {
			attackTags.add(new Tag("rebellion", "rebellion and disorder weaken mortal minds\n+30% on ancient actions",
					TagEffect.MENTAL_BONUS, true, ChallengeType.MENTAL, 0.3f));
		}
		for (Tag tag : attackTags) {
			attack += tag.getValue();
		}
		attack = Math.min(1, Math.max(0, attack));

		challengeBonusList.setCurrentModel(attackTags);
		lblTotalattack.setText("" + Math.rint(attack * 100) + " %");

		// defense in this case is how much powerful is target: if he/she has
		// mental bonus/malus trait
		Actor defender = challenge.getFrom();
		List<Tag> defenseTag = getDefenseValue(defender, ChallengeType.MENTAL);
		defense = 0;
		Tag factionModifier = getFactionModifiers(defender);
		if (factionModifier != null) {
			defenseTag.add(factionModifier);
			Tag rebellionTag = new Tag("guild war", "humans thath fight for no reason\n-20% on defense",
					TagEffect.MENTAL_MALUS, false, ChallengeType.MENTAL, -0.2f);
			// if guild is at war, is a malus for the defender -20%
			defenseTag.add(rebellionTag);
		}
		for (Tag tag : defenseTag) {
			defense += tag.getValue();
		}
		defense = Math.min(1, Math.max(0, defense));

		challengeMalusList.setCurrentModel(defenseTag);

		lblTotaldefense.setText("" + Math.rint(defense * 100) + " %");
	}

	private Tag getFactionModifiers(Actor defender) {
		if (defender.getFaction() != null) {
			if (defender.getFaction().getStatus().equals(FactionStatusEnum.WAR)) {
				return new Tag("faction war", "divided in small groups that fight one each another\n-20% on defense",
						TagEffect.SOCIAL_MALUS, false, ChallengeType.MENTAL, -0.2f);
			}
		}
		return null;
	}

	private List<Tag> getDefenseValue(Actor defender, ChallengeType type) {
		if (defender.getTags() != null) {
			if (type.equals(ChallengeType.ANY)) {
				return defender.getTags();
			} else {
				return defender.getTags().stream().filter(t -> t.getChallengeType().equals(type))
						.collect(Collectors.toList());
			}
		}
		return new ArrayList<>();
	}

	public void refresh() {
	}
}
