package it.randomtower.infiltration.model.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.tag.Tag;
import it.randomtower.infiltration.model.faction.Faction;

public class Actor {

	private String name;
	private String description;
	private ActorRole role;
	private Faction faction;
	private Stats stats;
	private boolean corrupted;
	private Map<Actor, Relationship> relations = new HashMap<>();
	private List<String> questLog = new ArrayList<>();
	private boolean doingAction = false;
	private int actionCounter;
	private ActionEnum actionType;
	private List<Tag> tags = new ArrayList<>();
	private double x;
	private double y;
	private boolean dead;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ActorRole getRole() {
		return role;
	}

	public void setRole(ActorRole role) {
		this.role = role;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public void setStats(Stats stats) {
		this.stats = stats;
	}

	public void putRelation(Actor actor, Relationship rel) {
		relations.put(actor, rel);
	}

	public Relationship getRelation(Actor a) {
		return relations.get(a);
	}

	public Set<Entry<Actor, Relationship>> getRelations() {
		return relations.entrySet();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Actor) {
			return ((Actor) obj).getName().equals(name);
		}
		return false;
	}

	public int getFollowers() {
		return stats.followers;
	}

	public void setFollowers(int followers) {
		this.stats.followers = followers;
	}

	public void addFollowers(int v) {
		stats.followers += v;
		stats.followers = Math.max(0, Math.min(stats.followers, 10));
	}

	public int getMoney() {
		return stats.money;
	}

	public void setMoney(int money) {
		this.stats.money = money;
	}

	public void addMoney(int v) {
		this.stats.money += v;
		stats.money = Math.max(0, Math.min(stats.money, 10));
	}

	public int getFollowersBonus() {
		return stats.followersBonus;
	}

	public void setFollowersBonus(int followersBonus) {
		this.stats.followersBonus = followersBonus;
	}

	public int getMoneyBonus() {
		return stats.moneyBonus;
	}

	public void setMoneyBonus(int moneyBonus) {
		this.stats.moneyBonus = moneyBonus;
	}

	public int getArcane() {
		return stats.arcane;
	}

	public void setArcane(int arcane) {
		this.stats.arcane = arcane;
	}

	public int getInfluence() {
		return stats.influence;
	}

	public void setInfluence(int influence) {
		this.stats.influence = influence;
	}

	public void addInfluence(int v) {
		stats.influence = Math.max(0, Math.min(stats.influence, 10));
		this.stats.influence += v;
	}

	public void addArcane(int v) {
		this.stats.arcane += v;
		stats.arcane = Math.max(0, Math.min(stats.arcane, 10));
	}

	@Override
	public String toString() {
		return String.format("%s [ influenza= %d, seguaci= %d, denaro = %d, arcano = %d ]", getName(), getInfluence(),
				getFollowers(), getMoney(), getArcane());
	}

	public List<String> getQuestLog() {
		return questLog;
	}

	public void addQuestLog(String log) {
		this.questLog.add(log);
	}

	public boolean isDoingActions() {
		return doingAction;
	}

	public void setDoingAction(boolean value) {
		this.doingAction = value;
	}

	public int getCurrentActionCounter() {
		return actionCounter;
	}

	public void setActionCounter(int actionCounter) {
		this.actionCounter = actionCounter;
	}

	public ActionEnum getCurrentActionType() {
		return actionType;
	}

	public void setCurrenctActionType(ActionEnum actionType) {
		this.actionType = actionType;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void addTag(Tag tag) {
		if (!tags.contains(tag)) {
			if (tags.stream().noneMatch(t -> t.getChallengeType().equals(tag.getChallengeType()))) {
				tags.add(tag);
			}
		}
	}

	public int getAttack() {
		return stats.attack;
	}

	public int getHealth() {
		return stats.health;
	}

	public int getMadness() {
		return stats.madness;
	}

	public int addMadness(int n) {
		stats.madness += n;
		stats.madness = Math.max(0, Math.min(stats.madness, 10));
		return stats.madness;
	}

	public int addHealth(int n) {
		stats.health += n;
		stats.health = Math.max(0, Math.min(stats.health, 10));
		return stats.health;

	}

	public void setMadness(int n) {
		stats.madness = n;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean isCorrupted() {
		return corrupted;
	}

	public void setCorrupted(boolean corrupted) {
		this.corrupted = corrupted;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
