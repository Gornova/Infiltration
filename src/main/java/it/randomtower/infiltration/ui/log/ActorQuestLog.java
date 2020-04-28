package it.randomtower.infiltration.ui.log;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.randomtower.infiltration.model.actor.Actor;

@SuppressWarnings("serial")
public class ActorQuestLog extends JPanel {

	private StringBuilder logText;
	private JTextArea logPanel;
	// TODO: to common class with other panels ?
	private Color color = new Color(74, 80, 80);

	public ActorQuestLog() {
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.logText = new StringBuilder();
		setLayout(null);

		JLabel lblEvemt = new JLabel("Quest log");
		lblEvemt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEvemt.setForeground(Color.WHITE);
		lblEvemt.setBounds(218, 11, 71, 19);
		add(lblEvemt);

		logPanel = new JTextArea("");
		logPanel.setLineWrap(true);
		logPanel.setWrapStyleWord(true);
		logPanel.setEditable(false);
		logPanel.setBackground(color);
		logPanel.setForeground(Color.white);
		logPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		JScrollPane scrollPane = new JScrollPane(logPanel);
		scrollPane.setBounds(20, 49, 469, 313);
		add(scrollPane);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(237, 373, 32, 16);
		btnNewButton.setBackground(color);
		btnNewButton.setBorder(new LineBorder(Color.black));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		add(btnNewButton);
	}

	public void displayLog(Actor actor) {
		if (actor != null && actor.getQuestLog() != null && !actor.getQuestLog().isEmpty()) {
			for (String log : actor.getQuestLog()) {
				logText.append(log);
				logText.append("\n");
				logPanel.setText(logText.toString());
				repaint();
			}
		}
	}

}
