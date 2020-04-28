package it.randomtower.infiltration.ui.log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import it.randomtower.infiltration.ui.ModernScrollPane;

@SuppressWarnings("serial")
public class LogPanel extends JPanel {

	private StringBuilder logText;
	private JTextArea logPanel;
	// TODO: to common class with other panels ?
	private Color color = new Color(74, 80, 80);
	private JButton therosLogButton;
	private JButton lanitirLogButton;
	private JButton anriosLogButton;
	private JButton xalingLogButton;
	private JButton vokarLogButton;

	private Map<String, ArrayList<String>> logMap = new HashMap<>();

	private boolean theros = false;
	private boolean lanitir = false;
	private boolean anrios = false;
	private boolean xalin = false;
	private boolean vokar = false;

	public LogPanel() {
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.logText = new StringBuilder();

		logPanel = new JTextArea(logText.toString());
		logPanel.setLineWrap(true);
		logPanel.setWrapStyleWord(true);
		logPanel.setBounds(0, 0, 0, 0);
		logPanel.setEditable(false);
		logPanel.setRows(4);
		logPanel.setBackground(color);
		logPanel.setForeground(Color.white);
		logPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 0, 0);
		panel.setBackground(color);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(5, 1, 0, 0));

		therosLogButton = new JButton("Theros");
		therosLogButton.setBorderPainted(theros);
		therosLogButton.setForeground(Color.WHITE);
		therosLogButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		therosLogButton.setContentAreaFilled(false);
		therosLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				theros = !theros;
				refresh();
			}
		});
		panel.add(therosLogButton);

		lanitirLogButton = new JButton("Lanitir");
		lanitirLogButton.setBorderPainted(lanitir);
		lanitirLogButton.setForeground(Color.WHITE);
		lanitirLogButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lanitirLogButton.setContentAreaFilled(false);
		lanitirLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lanitir = !lanitir;
				refresh();
			}
		});
		panel.add(lanitirLogButton);

		anriosLogButton = new JButton("Anrios");
		anriosLogButton.setBorderPainted(anrios);
		anriosLogButton.setForeground(Color.WHITE);
		anriosLogButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		anriosLogButton.setContentAreaFilled(false);
		anriosLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				anrios = !anrios;
				refresh();
			}
		});
		panel.add(anriosLogButton);

		xalingLogButton = new JButton("Xalin");
		xalingLogButton.setBorderPainted(xalin);
		xalingLogButton.setForeground(Color.WHITE);
		xalingLogButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		xalingLogButton.setContentAreaFilled(false);
		xalingLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xalin = !xalin;
				refresh();
			}
		});
		panel.add(xalingLogButton);

		vokarLogButton = new JButton("Vokar");
		vokarLogButton.setBorderPainted(vokar);
		vokarLogButton.setForeground(Color.WHITE);
		vokarLogButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		vokarLogButton.setContentAreaFilled(false);
		vokarLogButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vokar = !vokar;
				refresh();
			}
		});
		panel.add(vokarLogButton);

		ModernScrollPane scrollLogPanel = new ModernScrollPane(logPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollLogPanel);
	}

	public void appendLog(String city, String log) {
		if (logMap.containsKey(city)) {
			ArrayList<String> msgs = logMap.get(city);
			msgs.add(log);
			logMap.put(city, msgs);

		} else {
			ArrayList<String> msgs = new ArrayList<>();
			msgs.add(log);
			logMap.put(city, msgs);
		}
		refresh();
	}

	private void refresh() {
		therosLogButton.setBorderPainted(theros);
		lanitirLogButton.setBorderPainted(lanitir);
		anriosLogButton.setBorderPainted(anrios);
		xalingLogButton.setBorderPainted(xalin);
		vokarLogButton.setBorderPainted(vokar);
		StringBuffer buffer = new StringBuffer();
		for (Entry<String, ArrayList<String>> entry : logMap.entrySet()) {
			if (theros) {
				if (entry.getKey().equalsIgnoreCase("theros")) {
					for (String e : entry.getValue()) {
						buffer.append(e);
						buffer.append("\n");
					}
				}
			}
			if (lanitir) {
				if (entry.getKey().equalsIgnoreCase("lanitir")) {
					for (String e : entry.getValue()) {
						buffer.append(e);
						buffer.append("\n");
					}
				}
			}
			if (anrios) {
				if (entry.getKey().equalsIgnoreCase("anrios")) {
					for (String e : entry.getValue()) {
						buffer.append(e);
						buffer.append("\n");
					}
				}
			}
			if (xalin) {
				if (entry.getKey().equalsIgnoreCase("xalin")) {
					for (String e : entry.getValue()) {
						buffer.append(e);
						buffer.append("\n");
					}
				}
			}
			if (vokar) {
				if (entry.getKey().equalsIgnoreCase("vokar")) {
					for (String e : entry.getValue()) {
						buffer.append(e);
						buffer.append("\n");
					}
				}
			}
			if (entry.getKey().isEmpty()) {
				for (String e : entry.getValue()) {
					buffer.append(e);
					buffer.append("\n");
				}
			}
		}
		logPanel.setText("");
		logPanel.setText(buffer.toString());
		repaint();
	}

}
