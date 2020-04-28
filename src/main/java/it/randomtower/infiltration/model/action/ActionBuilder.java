package it.randomtower.infiltration.model.action;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.scenario.Scenario;

public final class ActionBuilder {

	public static final Action buildCorrupt(Actor target, int turns, Poi poi) {
		Action action = new CorruptAction();
		action.setActor(target);
		action.setTime(turns);
		action.setPoi(poi);
		return action;
	}

	public static Action buildCalling(Actor target, int cost, Poi poi) {
		Action action = new CallingAction();
		action.setActor(target);
		action.setTime(cost);
		action.setPoi(poi);
		return action;
	}

	public static Action buildManipulate(Scenario scenario, Actor from, int cost, Actor to, Poi poi) {
		Action action = new ManipulateAction(scenario);
		action.setActor(from);
		action.setTime(cost);
		action.setPoi(poi);
		action.setTarget(to);
		return action;
	}

	public static Action buildInvestigate(Scenario scenario, Actor from, Poi poi, int cost) {
		Action action = new InvestigateAction(scenario);
		action.setActor(from);
		action.setPoi(poi);
		action.setTime(cost);
		return action;
	}

}
