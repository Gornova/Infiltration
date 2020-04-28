package it.randomtower.infiltration.animation;

import java.awt.Image;

import de.anormalmedia.vividswinganimations.api.Moveable;
import de.anormalmedia.vividswinganimations.listener.AnimationListener;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.ui.ImageButton;

public class PngIcon extends ImageButton implements Moveable {

	private static final long serialVersionUID = 6294971624472596050L;
	private Actor actor;
	private MoveAnimation moveAnimation;

	public PngIcon(Image image, Image onOverImage, String tooltip, int x, int y, int w, int h, Actor actor) {
		super(image, onOverImage, tooltip, x, y, w, h);
		this.actor = actor;
	}

	public void animate(int tx, int ty, AnimationListener animationListener) {
		moveAnimation = new MoveAnimation(tx - 32, ty - 32, this);
		moveAnimation.animate(animationListener);
	}

	@Override
	public double getLocationX() {
		return actor.getX();
	}

	@Override
	public void setLocationX(double x) {
		actor.setX(x);
		setLocation((int) getLocationX(), (int) getLocationY());
	}

	@Override
	public double getLocationY() {
		return actor.getY();
	}

	@Override
	public void setLocationY(double y) {
		actor.setY(y);
		setLocation((int) getLocationX(), (int) getLocationY());
	}

}
