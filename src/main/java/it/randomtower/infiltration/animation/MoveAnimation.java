package it.randomtower.infiltration.animation;

import de.anormalmedia.vividswinganimations.api.Moveable;
import de.anormalmedia.vividswinganimations.bounds.LinearInterpolationAnimation2;
import de.anormalmedia.vividswinganimations.listener.AnimationListener;
import de.anormalmedia.vividswinganimations.runner.SequentialAnimationRunner;

public class MoveAnimation {

	private int ex;
	private int ey;
	private Moveable element;
	private LinearInterpolationAnimation2 interpolation;

	public MoveAnimation(int ex, int ey, Moveable element) {
		this.ex = ex;
		this.ey = ey;
		this.element = element;
		this.interpolation = new LinearInterpolationAnimation2(element, ex, ey);
	}

	public void animate(AnimationListener animationListener) {
		// sync with game timer!
		interpolation.setDuration(1000);
		interpolation.addAnimationListener(animationListener);

		SequentialAnimationRunner defaultAnimator = new SequentialAnimationRunner();
		defaultAnimator.addAnimation(interpolation);

		defaultAnimator.start();
	}
}
