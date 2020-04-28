package it.randomtower.infiltration.ui.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import it.randomtower.infiltration.citysimulation.Combat;
import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.ui.map.MapPanel;

@SuppressWarnings("serial")
public class FightPanel extends JPanel {

	// TODO: to common class with other panels ?
	// private Color color = new Color(74, 80, 80);
	private Color color = new Color(124, 126, 126);
	private Challenge challenge;
	private JLabel lblTitle;
	private MapPanel mapPanel;
	private JLabel lblTotalattack;
	private JPanel bonusPanel;
	private JPanel malusPanel;
	private JLabel lblTotaldefense;
	private float attack;
	private float defense;
	private Random rnd = new Random();
	private JButton btnCancel;
	private JLabel lblFightResult;
	private JButton btnContinue;
	private ActionEnum playerAction;
	private Actor attacker;
	private Actor defender;
	private JLabel lblAttackerName;
	private JLabel lblDefenderName;
	private JLabel lblAttackValue;
	private JLabel lblDefenseValue;
	private JLabel lblFollowers;
	private JLabel lblFollowersDefender;
	private JLabel lblFollowersAttackValue;
	private JLabel lblFollowersDefendValue;

	public FightPanel(MapPanel mapPanel) throws IOException {
		this.mapPanel = mapPanel;
		setBounds(256, 128, 512, 400);
		setBackground(color);
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		setLayout(null);

		lblTitle = new JLabel("Combat");
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
		lblAttack.setBounds(10, 67, 67, 14);
		lblAttack.setForeground(Color.WHITE);

		bonusPanel.add(lblAttack);

		lblTotalattack = new JLabel("TotalAttack");
		lblTotalattack.setBounds(150, 11, 60, 14);
		bonusPanel.add(lblTotalattack);

		lblAttackerName = new JLabel("Attacker");
		lblAttackerName.setForeground(Color.WHITE);
		lblAttackerName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAttackerName.setBounds(10, 11, 103, 14);
		bonusPanel.add(lblAttackerName);

		lblAttackValue = new JLabel("TotalAttack");
		lblAttackValue.setBounds(150, 67, 60, 14);
		bonusPanel.add(lblAttackValue);

		lblFollowers = new JLabel("Followers");
		lblFollowers.setForeground(Color.WHITE);
		lblFollowers.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFollowers.setBounds(10, 105, 67, 14);
		bonusPanel.add(lblFollowers);

		lblFollowersAttackValue = new JLabel("TotalAttack");
		lblFollowersAttackValue.setBounds(150, 105, 60, 14);
		bonusPanel.add(lblFollowersAttackValue);

		malusPanel = new JPanel();
		malusPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		malusPanel.setBounds(270, 48, 220, 298);
		malusPanel.setBackground(color);
		malusPanel.setLayout(null);

		add(malusPanel);

		JLabel lblDefense = new JLabel("Defender");
		lblDefense.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDefense.setBounds(10, 66, 67, 14);
		lblDefense.setForeground(Color.WHITE);

		malusPanel.add(lblDefense);

		lblTotaldefense = new JLabel("TotalDefense");
		lblTotaldefense.setBounds(136, 11, 67, 14);
		malusPanel.add(lblTotaldefense);

		lblDefenderName = new JLabel("Defender");
		lblDefenderName.setForeground(Color.WHITE);
		lblDefenderName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDefenderName.setBounds(10, 11, 92, 14);
		malusPanel.add(lblDefenderName);

		lblDefenseValue = new JLabel("TotalAttack");
		lblDefenseValue.setBounds(136, 66, 60, 14);
		malusPanel.add(lblDefenseValue);

		lblFollowersDefender = new JLabel("Followers");
		lblFollowersDefender.setForeground(Color.WHITE);
		lblFollowersDefender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFollowersDefender.setBounds(10, 108, 67, 14);
		malusPanel.add(lblFollowersDefender);

		lblFollowersDefendValue = new JLabel("TotalAttack");
		lblFollowersDefendValue.setBounds(136, 108, 60, 14);
		malusPanel.add(lblFollowersDefendValue);

		btnContinue = new JButton("Combat");
		btnContinue.setBounds(20, 366, 89, 23);
		btnContinue.setBackground(color);
		btnContinue.setBorder(new LineBorder(Color.black));
		btnContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String result = Combat.combat(mapPanel.getScenario(), attacker, defender);
				lblFightResult.setText(result);
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

		lblFightResult = new JLabel("Select continue to start fight");
		lblFightResult.setBounds(148, 370, 220, 14);
		lblFightResult.setForeground(Color.WHITE);
		add(lblFightResult);
	}

	public void setupCombat(Actor attacker, Actor defender) {
		this.attacker = attacker;
		this.defender = defender;
		this.lblAttackerName.setText(attacker.getName());
		this.lblDefenderName.setText(defender.getName());
		this.lblAttackValue.setText("" + attacker.getAttack());
		this.lblDefenseValue.setText("" + defender.getAttack());
		this.lblFollowersAttackValue.setText("" + attacker.getFollowers());
		this.lblFollowersDefendValue.setText("" + defender.getFollowers());
		int totAttack = attacker.getAttack() + attacker.getFollowers();
		int totDefend = defender.getAttack() + defender.getFollowers();

		this.lblTotalattack.setText("" + totAttack);
		this.lblTotaldefense.setText("" + totDefend);
	}

	public void refresh() {
	}
}
