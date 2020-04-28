package it.randomtower.infiltration.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Stats;
import it.randomtower.infiltration.model.actor.StatsBuilder;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.model.poi.StatusEnum;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class RebellionSystem extends BaseSystem {

	private Random rnd = new Random();

	private Map<String, Integer> timers = new HashMap<>();

	@Override
	public void act(Scenario scenario) {
		List<CityPoi> cities = scenario.getPois().stream().filter(p -> p.getType() == PoiType.CITY)
				.map(p -> (CityPoi) p).collect(Collectors.toList());
		for (CityPoi city : cities) {
			if (city.getStatus() == StatusEnum.PEACE) {
				// for every city, if rebellion is more than 0, there is a
				// little
				// chance that rebellion spreads
				if (city.getRebellion() > 0 && rnd.nextFloat() < 0.02f) {
					city.addRebellion(1);
					MapPanel.appendLog(city.getName(), "City " + city.getName() + " rebellion +1");
				}
				// chance that rebellion stops
				if (city.getRebellion() > 5 && rnd.nextFloat() < 0.10f) {
					city.addRebellion(-1);
					MapPanel.appendLog(city.getName(), "City " + city.getName() + " rebellion -1");
				}
				// TODO: this random values could be influenced also by some
				// world
				// chaos value ?
				// if rebellion is 10, starts a rebellion
				if (city.getRebellion() >= 10) {
					// rebellion starts!
					city.setStatus(StatusEnum.REBELLION);
					city.getActors().forEach(a -> a.addMadness(2));
					// city raise and army to prepare to fight
					Stats armyStats = StatsBuilder.buildArmy(city);
					city.setArmyStats(armyStats);
					// and rebels too
					Stats rebellionStats = StatsBuilder.buildRebellionArmy(city);
					city.setRebellionStats(rebellionStats);
					MapPanel.displayEvent(String.format(Msg.getText(Msg.REBELLION_START), city.getName(),
							rebellionStats.attack, armyStats.attack));
				}
			} else if (city.getStatus().equals(StatusEnum.REBELLION)) {
				// every turn, two opposing forces take a fight until one wins
				if (!timers.containsKey(city.getName())) {
					// rebellion vs army fight will be longer if army attack+hp
					// for
					// every
					// side is near
					// so for example:
					// delta = 0 => 15 turns
					// delta = 2 => 13 turns
					// delta >= 10 => 5 turns
					Stats armyStats = city.getArmyStats();
					Stats rebellionStats = city.getRebellionStats();
					int delta = (rebellionStats.attack + rebellionStats.health) - (armyStats.attack + armyStats.health);
					delta = delta < 0 ? delta * -1 : delta;
					int turns = 15 - delta;
					timers.put(city.getName(), turns);
				} else {
					int t = timers.get(city.getName());
					t--;
					timers.put(city.getName(), t);
					if (t <= 0) {
						int r = scenario.armyFight(city.getRebellionStats(), city.getArmyStats());
						// MapPanel.displayEvent("In city of " + city.getName() + ", " + r);
						if (r != 0) {
							// TODO: for now a victory is only to exit from
							// rebellion
							// status
							city.setStatus(StatusEnum.PEACE);
							city.resetRebellion();
							if (r == 1) {
								MapPanel.displayEvent(
										"In city of " + city.getName() + ", " + Msg.getText(Msg.REBELLION_WINS));
							} else {
								MapPanel.displayEvent(
										"In city of " + city.getName() + ", " + Msg.getText(Msg.REBELLION_LOSE));
							}
						}
					}
				}
			}
		}
	}

}
