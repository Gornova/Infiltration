package it.randomtower.infiltration;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import it.randomtower.infiltration.ui.MultipleLineTooltips;
import it.randomtower.infiltration.ui.map.MapPanel;

public class Main {

	private static JFrame frmInfiltrationDemo;
	private MapPanel mapPanel;
	// TODO: common color
	private Color color = new Color(74, 80, 80);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmInfiltrationDemo.setLocationRelativeTo(null);
					window.frmInfiltrationDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		try {
			initializeUi();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeUi() throws IOException {
		MultipleLineTooltips.installUI();

		UIManager.put("ScrollBar.background", color);
		UIManager.put("ScrollBar.darkShadow", color);
		UIManager.put("ScrollBar.shadow", color);
		UIManager.put("ScrollBar.width", new Integer(10));

		frmInfiltrationDemo = new JFrame();
		frmInfiltrationDemo.setBounds(0, 0, 1024, 768);
		frmInfiltrationDemo.getContentPane().setLayout(null);
		frmInfiltrationDemo.setResizable(false);
		frmInfiltrationDemo.setTitle(Msg.getText(Msg.TITLE));
		frmInfiltrationDemo.setBounds(100, 100, 1024, 768);
		frmInfiltrationDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmInfiltrationDemo
				.setIconImages(Arrays.asList(getFileResource("icon16x16.png"), getFileResource("icon32x32.png")));

		mapPanel = new MapPanel(getFileResource("kingdomBlackBorder.png"), this);

		frmInfiltrationDemo.getContentPane().add(mapPanel, 0);

	}

	public void refresh() {
		frmInfiltrationDemo.getContentPane().validate();
		frmInfiltrationDemo.getContentPane().repaint();
	}

	public static JFrame getFrame() {
		return frmInfiltrationDemo;
	}

	public static BufferedImage getFileResource(final String fileName) throws IOException {
		ClassLoader classLoader = Main.class.getClassLoader();
		try {
			return ImageIO.read(classLoader.getResourceAsStream("resources/" + fileName));
		} catch (IOException ioe) {
			return ImageIO.read(classLoader.getResourceAsStream(fileName));
		}
	}

}
