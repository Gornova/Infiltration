package it.randomtower.infiltration.model.action;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;

public abstract class BaseAction implements Action {

	private String name;
	private ActionEnum type;
	private boolean finished;
	protected int counter;
	protected int turns;
	private Actor actor;
	private Poi poi;
	private Actor target;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActionEnum getType() {
		return type;
	}

	public void setType(ActionEnum type) {
		this.type = type;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setTime(int turns) {
		this.turns = turns;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Poi getPoi() {
		return poi;
	}

	public void setPoi(Poi poi) {
		this.poi = poi;
	}

	public Actor getTarget() {
		return target;
	}

	public void setTarget(Actor target) {
		this.target = target;
	}

	public int getTime() {
		return turns;
	}

}
