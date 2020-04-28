package it.randomtower.infiltration.model.poi;

import java.util.ArrayList;
import java.util.List;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.Stats;

public abstract class BasePoi implements Poi {

	private long id;

	private String name;

	private String description;

	private PoiType type;

	private StatusEnum status;

	private List<Actor> actors = new ArrayList<>();
	private List<Secret> secrets = new ArrayList<>();

	private int population;

	private int food;

	private int work;

	private int money;

	private int rebellion;

	private Stats armyStats;

	private Stats rebellionStats;

	private int clue;

	private String nickname;

	private float x;
	private float y;

	private List<String> connection;

	private boolean hidden;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PoiType getType() {
		return type;
	}

	public void setType(PoiType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
		addActorCallBack(actor);
	}

	protected void addActorCallBack(Actor actor) {
		// for further use
	}

	public int getPopulation() {
		return population;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getWork() {
		return work;
	}

	public void setWork(int work) {
		this.work = work;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public void add(Secret secret) {
		this.secrets.add(secret);
	}

	public List<Secret> getSecrets() {
		return secrets;
	}

	public int getRebellion() {
		return rebellion;
	}

	public int addRebellion(int r) {
		if (rebellion + r >= 0 && rebellion + r <= 10) {
			this.rebellion += r;
		}
		return rebellion;
	}

	public Stats getArmyStats() {
		return armyStats;
	}

	public void setArmyStats(Stats armyStats) {
		this.armyStats = armyStats;
	}

	public Stats getRebellionStats() {
		return rebellionStats;
	}

	public void setRebellionStats(Stats rebellionStats) {
		this.rebellionStats = rebellionStats;
	}

	public void addPopulation(int p) {
		if (population + p > 0 && population + p < 20000) {
			this.population += p;
		}
	}

	public void addClue(int c) {
		this.clue += c;
	}

	public int getClue() {
		return this.clue;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setConnection(List<String> connection) {
		this.connection = connection;
	}

	public List<String> getConnection() {
		return connection;
	}

	public abstract void removeActor(Actor png);

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void resetRebellion() {
		this.rebellion = 0;
	}

}
