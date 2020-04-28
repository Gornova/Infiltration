package it.randomtower.infiltration.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.ActorBuilder;
import it.randomtower.infiltration.model.actor.Relationship;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class RecruitmentSystem extends BaseSystem {

	private float basePercentage;
	private Random rnd = new Random();

	public RecruitmentSystem(float basePercentage) {
		this.basePercentage = basePercentage;
	}

	@Override
	public void act(Scenario scenario) {
		List<CityPoi> cities = scenario.getPois().stream().filter(p -> p.getType() == PoiType.CITY)
				.map(p -> (CityPoi) p).collect(Collectors.toList());
		for (CityPoi city : cities) {
			if (city.getActors().size() < 3 && rnd.nextFloat() < basePercentage) {
				Actor a = ActorBuilder.buildRandomWithFaction(scenario);
				// TODO: add a random relation with someone in the city
				addRandomRelation(a, city.getActors());

				city.getActors().add((Actor) a);
				MapPanel.appendLog(city.getName(),
						"A new character " + a.getName() + " attract your attention in " + city.getName());
				return;
			}
		}
	}

	// TODO: duplicated see Scenario.addRandomRelation
	private void addRandomRelation(Actor actor, List<Actor> collect) {
		List<Actor> temp = new ArrayList<>(collect);
		temp.remove(actor);
		if (!temp.isEmpty()) {
			actor.putRelation(temp.get(rnd.nextInt(temp.size())), Relationship.HATE);
		} else {
			// first, need wait for next recruit
		}
	}

}
