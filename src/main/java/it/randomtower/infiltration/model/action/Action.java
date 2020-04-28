package it.randomtower.infiltration.model.action;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;

public interface Action {

	public String getName();

	public ActionEnum getType();

	public void setTime(int turns);

	public int getTime();

	public boolean isFinished();

	public void setActor(Actor actor);

	public Actor getActor();

	public void act();

	public void setPoi(Poi poi);

	public Poi getPoi();

	public void setTarget(Actor to);

}
