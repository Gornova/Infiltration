package it.randomtower.infiltration.system;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class FoodSystem extends BaseSystem {

	private static final int MAX_FOOD = 10;
	private Random rnd = new Random();

	public FoodSystem() {

	}

	@Override
	public void act(Scenario scenario) {
		// for every city
		List<CityPoi> cities = scenario.getPois().stream().filter(p -> p.getType() == PoiType.CITY)
				.map(p -> (CityPoi) p).collect(Collectors.toList());
		for (CityPoi city : cities) {
			int food = city.getFood();
			if (food == 0) {
				MapPanel.displayEvent(String.format(Msg.getText(Msg.FAMINE_START), city.getName()));
				city.addPopulation(-1000);
				city.addRebellion(2);
				food++;
			}
			if (food <= 3 && rnd.nextFloat() < 0.05f) {
				food -= 1;
				MapPanel.appendLog(city.getName(), "City " + city.getName() + " lose food. Food=" + food);
			}
			if (food > 3 && rnd.nextFloat() >= 0.90f) {
				MapPanel.appendLog(city.getName(), "City " + city.getName() + " gain food. Food=" + food);
				food += 1;
			}
			if (food >= MAX_FOOD) {
				food = MAX_FOOD;
			} else if (food <= 0) {
				food = 0;
			}
			city.setFood(food);
			if (city.getPopulation() == 0) {
				// CITY DESTROYED ?
			}
		}
	}

}
