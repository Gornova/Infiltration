package it.randomtower.infiltration.ui.event;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import it.randomtower.infiltration.timer.GameTimer;

@SuppressWarnings("serial")
public class EventPanel extends JPanel {

	private StringBuilder logText;
	private JTextPane logPanel;
	private Color color = new Color(74, 80, 80);
	private GameTimer gameTimer;
	private JLabel lblEvemt;

	public EventPanel(GameTimer gameTimer) throws IOException {
		this.gameTimer = gameTimer;
		setBackground(color);
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setLayout(null);

		logPanel = new JTextPane();
		logPanel.setContentType("text/html");// set content as html
		// logPanel.setLineWrap(true);
		logPanel.setBounds(20, 47, 471, 292);
		// logPanel.setWrapStyleWord(true);
		logPanel.setEditable(false);
		logPanel.setBackground(color);
		logPanel.setForeground(Color.WHITE);
		logPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

		logPanel.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent hle) {
				if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
					System.out.println(hle.getURL());
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(hle.getURL().toURI());
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		add(logPanel);

		lblEvemt = new JLabel("Event");
		lblEvemt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEvemt.setForeground(Color.WHITE);
		lblEvemt.setBounds(10, 11, 512, 25);
		add(lblEvemt);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(219, 350, 90, 23);
		btnNewButton.setBackground(color);
		btnNewButton.setBorder(new LineBorder(Color.black));
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				// gameTimer.start();
			}
		});
		add(btnNewButton);
	}

	public void displayEvent(String text, String title) {
		this.lblEvemt.setText(title);
		logPanel.setText("<font color=white>" + text + "</font>");
		setVisible(true);
		// gameTimer.stop();
	}

}
