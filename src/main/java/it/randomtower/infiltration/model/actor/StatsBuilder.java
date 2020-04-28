package it.randomtower.infiltration.model.actor;

import java.util.Random;

import it.randomtower.infiltration.model.poi.CityPoi;

public final class StatsBuilder {

	private static Random rnd = new Random();

	public static final Stats build(int attack, int hp, int madness, int arcane, int money) {
		Stats stats = new Stats();
		stats.attack = attack;
		stats.health = hp;
		stats.madness = madness;
		stats.arcane = arcane;
		stats.money = money;
		return stats;
	}

	public static Stats buildArmy(CityPoi city) {
		Stats stats = new Stats();
		// TODO: base formula, could be improved with other modifiers and
		// special events ? Based only on population
		int base = Math.max(city.getPopulation() / 1000, 0);
		stats.attack = base;
		stats.health = base;
		return stats;
	}

	public static Stats buildRebellionArmy(CityPoi city) {
		// for rebellion is half of the population + a random value from 2000 to
		// 5000
		Stats stats = new Stats();
		// TODO: base formula, could be improved with other modifiers and
		// special events ? Based only on population
		int base = Math.max((city.getPopulation() / 2 + 2000 + rnd.nextInt(4) * 1000) / 1000, 0);
		stats.attack = base;
		stats.health = base;
		return stats;
	}

}
