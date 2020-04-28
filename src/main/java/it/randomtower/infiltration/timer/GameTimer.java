package it.randomtower.infiltration.timer;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameTimer {

	private Timer timer;
	public boolean pause;

	public GameTimer(int speed, ActionListener listener) {
		timer = new Timer(speed, listener);
		timer.setInitialDelay(speed);
		timer.stop();
		pause = true;
	}

	public void start() {
		timer.start();
		pause = false;
	}

	public void stop() {
		timer.stop();
		pause = true;
	}

}
