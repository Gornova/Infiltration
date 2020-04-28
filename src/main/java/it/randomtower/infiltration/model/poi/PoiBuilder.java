package it.randomtower.infiltration.model.poi;

import java.util.List;

public final class PoiBuilder {

	private static long idCounter = 0;

	public static final CityPoi buildCity(String name, String description, String nickname, float x, float y,
			StatusEnum status, int food, int population, int work, int money, List<String> connection) {
		CityPoi city = new CityPoi(name);
		city.setId(++idCounter);
		city.setDescription(description);
		city.setX(x);
		city.setY(y);
		city.setType(PoiType.CITY);
		city.setStatus(status);
		city.setFood(food);
		city.addPopulation(population);
		city.setWork(work);
		city.setMoney(money);
		city.setNickname(nickname);
		city.setConnection(connection);

		city.addDistricts();

		return city;
	}

	public static RuinPoi buildRuin(String name, String description, String nickname, int x, int y,
			List<String> connections, Boolean hidden) {
		RuinPoi ruin = new RuinPoi(name);
		ruin.setId(++idCounter);
		ruin.setDescription(description);
		ruin.setX(x);
		ruin.setY(y);
		ruin.setType(PoiType.RUIN);
		ruin.setConnection(connections);
		ruin.setHidden(hidden != null ? hidden : false);
		ruin.setStatus(StatusEnum.PEACE);

		ruin.addDistricts();

		return ruin;
	}

}
