package it.randomtower.infiltration.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import it.randomtower.infiltration.timer.GameTimer;

@SuppressWarnings("serial")
public class TurnButton extends ImageButton {

	private GameTimer gameTimer;
	private BufferedImage imagePlay;
	private BufferedImage imagePause;
	private BufferedImage imagePlayRoll;
	private BufferedImage imagePauseRoll;

	public TurnButton(BufferedImage imagePlay, BufferedImage imagePlayRoll, BufferedImage imagePause,
			BufferedImage imagePauseRoll, int x, int y, GameTimer gameTimer) {
		super(imagePause, imagePause, "Next turn", x, y, 32, 32);
		this.gameTimer = gameTimer;
		this.imagePlay = imagePlay;
		this.imagePlayRoll = imagePlayRoll;
		this.imagePause = imagePause;
		this.imagePauseRoll = imagePauseRoll;
		pause();
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (gameTimer.pause) {
					start();
				} else {
					pause();
				}
				repaint();
			}
		});
	}

	public void pause() {
		setIcon(new ImageIcon(imagePlay));
		setRolloverIcon(new ImageIcon(imagePlayRoll));
		gameTimer.stop();
		setToolTipText("Play");
	}

	public void start() {
		setIcon(new ImageIcon(imagePause));
		setRolloverIcon(new ImageIcon(imagePauseRoll));
		gameTimer.start();
		setToolTipText("Pause");
	}

}
