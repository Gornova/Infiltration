package it.randomtower.infiltration.system;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class ClueSystem extends BaseSystem {

	private Random rnd = new Random();

	public ClueSystem() {

	}

	@Override
	public void act(Scenario scenario) {
		// TODO: only for cities ?
		List<CityPoi> cities = scenario.getPois().stream().filter(p -> p.getType() == PoiType.CITY)
				.map(p -> (CityPoi) p).collect(Collectors.toList());
		for (CityPoi city : cities) {
			if (city.getClue() >= 10) {
				city.addClue(-city.getClue());
				Actor target = null;
				List<Actor> potential = city.getActors().stream().filter(a -> !a.isCorrupted())
						.collect(Collectors.toList());
				target = potential.get(rnd.nextInt(potential.size()));
				// create an investigation for it
				scenario.act(ActionEnum.INVESTIGATE, target, null, city);
				MapPanel.displayEvent(String.format(Msg.getText(Msg.CLUE_FOUND), target.getName(), city.getName()));
			}
		}
	}

}
