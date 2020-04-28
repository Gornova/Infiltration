package it.randomtower.infiltration.model.challenge;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;

public class Challenge {

	private ActionEnum action;
	private Actor from;
	private Actor to;
	private Poi poi;

	public Challenge(ActionEnum action, Actor from, Actor to, Poi poi) {
		this.action = action;
		this.from = from;
		this.to = to;
		this.poi = poi;
	}

	public ActionEnum getAction() {
		return action;
	}

	public Actor getFrom() {
		return from;
	}

	public Actor getTo() {
		return to;
	}

	public Poi getPoi() {
		return poi;
	}

}
