package it.randomtower.infiltration.citysimulation;

import it.randomtower.infiltration.model.actor.Actor;

public interface Simulation {

	public String getName();

	public Actor find(String padrone);

	public void remove(Quest quest);

	public void run();

	public void addPlayer(Actor actor);

	public void removePlayer(Actor png);

}
