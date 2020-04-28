package it.randomtower.infiltration.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.faction.Faction;
import it.randomtower.infiltration.model.faction.FactionBuilder;
import it.randomtower.infiltration.model.faction.FactionStatusEnum;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class FactionSystem extends BaseSystem {

	private Random rnd = new Random();

	private Map<String, Integer> counters;
	private Map<String, Integer> warExaustion;

	public FactionSystem() {
		counters = new HashMap<>();
		warExaustion = new HashMap<>();
		// init randomly counter reasoning for each faction
		for (String factionName : FactionBuilder.factions) {
			// TODO: also modifier from faction efficiency, relative power in
			// pois, etc.. for now random only
			counters.put(factionName, rnd.nextInt(10) + 10);
			warExaustion.put(factionName, 0);
		}
	}

	@Override
	public void act(Scenario scenario) {
		// for every faction, there are a value of "relation" (-100 / +100) with
		// other
		// faction
		// this relation has bonus/malus that is triggered by other
		// actions/events
		// every 10 turns, rolls a dice and see if faction want to start a war
		// or peace
		// for every city
		// List<CityPoi> cities = scenario.getPois().stream().filter(p ->
		// p.getType() == PoiType.CITY)
		// .map(p -> (CityPoi) p).collect(Collectors.toList());
		for (Faction faction : scenario.getFactions()) {
			if (faction.getName().equals(FactionBuilder.MONSTER_FACTION)) {
				// ignore monsters faction
				continue;
			}
			int c = counters.get(faction.getName()) - 1;
			if (c <= 0) {
				c = rnd.nextInt(10) + 10;
				evaluateRelation(faction, scenario);
			}
			counters.put(faction.getName(), c);
		}
	}

	private void evaluateRelation(Faction faction, Scenario scenario) {
		System.out.println("Evaluate relation for " + faction.getName());
		faction.getRelations().forEach((otherFaction, relationValue) -> {
			if (relationValue > 0 && faction.getStatus() == FactionStatusEnum.WAR) {
				// chance to trigger peace
				float v = rnd.nextFloat() * 100 + (relationValue / 4);
				if (v >= 100) {
					scenario.makePeace(faction, otherFaction);
					MapPanel.displayEvent(String.format(Msg.getText(Msg.WAR_END), faction.getName(), otherFaction));
					faction.getRelations().put(otherFaction, 0);
					// reset warExaustion
					warExaustion.put(faction.getName(), 0);
					warExaustion.put(otherFaction, 0);
				}
			} else if (relationValue < 0 && faction.getStatus() == FactionStatusEnum.PEACE) {
				// chance to trigger war
				float v = rnd.nextFloat() * 100 + (relationValue * -1) / 4;
				if (v >= 100) {
					scenario.makeWar(faction, otherFaction);
					MapPanel.displayEvent(String.format(Msg.getText(Msg.WAR_START), faction.getName(), otherFaction));
				}
			}
			if (faction.getStatus() == FactionStatusEnum.WAR) {
				warExaustion.put(faction.getName(), warExaustion.get(faction.getName()) + 1);
				if (warExaustion.get(faction.getName()) > 10) {
					// after a while, get better releation
					faction.getRelations().put(faction.getTarget(), 50);
				}
			}
		});

	}

}
