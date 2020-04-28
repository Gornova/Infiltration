package it.randomtower.infiltration.animation;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.BasePoi;

public class AnimationRequest {

	private Actor png;
	private BasePoi from;
	private BasePoi target;

	public AnimationRequest(Actor png, BasePoi from, BasePoi target) {
		this.png = png;
		this.from = from;
		this.target = target;
	}

	public Actor getPng() {
		return png;
	}

	public BasePoi getFrom() {
		return from;
	}

	public BasePoi getTarget() {
		return target;
	}

}
