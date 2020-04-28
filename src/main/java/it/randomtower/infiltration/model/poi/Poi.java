package it.randomtower.infiltration.model.poi;

import java.util.List;

import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.Stats;

public interface Poi {

	public long getId();

	public String getName();

	public String getDescription();

	public float getX();

	public float getY();

	public PoiType getType();

	public StatusEnum getStatus();

	public List<Actor> getActors();

	/**
	 * Total amout of population in the poi, used to raise army for any leading
	 * faction
	 */
	public int getPopulation();

	public void addPopulation(int p);

	/**
	 * Amount of food (min 0, max 10), used to calculate variation on population
	 */
	public int getFood();

	/**
	 * Amount of work production (min 0, max 10), represent how much quickly things
	 * are build
	 */
	public int getWork();

	/**
	 * Wealtch in the poi (min 0, max 10), how much speding is possible in the poi
	 */
	public int getMoney();

	public void add(Secret secret);

	public List<Secret> getSecrets();

	/**
	 * Amount of rebellion in the poi (min 0, max 10)
	 */
	public int getRebellion();

	public int addRebellion(int r);

	/**
	 * Local government army during rebellions or wars
	 */
	public Stats getArmyStats();

	/**
	 * Rebellion army
	 */
	public Stats getRebellionStats();

	public void addClue(int c);

	public int getClue();

	public List<District> getDistricts();

	public void resetRebellion();

}
