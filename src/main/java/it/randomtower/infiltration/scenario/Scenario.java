package it.randomtower.infiltration.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.animation.AnimationRequest;
import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.model.action.Action;
import it.randomtower.infiltration.model.action.ActionBuilder;
import it.randomtower.infiltration.model.action.ActionEnum;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.Relationship;
import it.randomtower.infiltration.model.actor.Stats;
import it.randomtower.infiltration.model.ancient.Ancient;
import it.randomtower.infiltration.model.faction.Faction;
import it.randomtower.infiltration.model.faction.FactionEnum;
import it.randomtower.infiltration.model.faction.FactionStatusEnum;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.model.poi.BaseSecret;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.model.poi.PoiType;
import it.randomtower.infiltration.model.poi.RuinPoi;
import it.randomtower.infiltration.system.GameSystem;
import it.randomtower.infiltration.ui.map.MapPanel;

public class Scenario {

	private static Scenario instance;
	private List<BasePoi> pois;
	private List<Faction> factions;
	private List<GameSystem> systems;
	private int turnCounter = 0;
	private Ancient ancient;
	private List<Action> actions;
	private int awarness = 0;
	private Random rnd = new Random();
	private List<AnimationRequest> animationRequest;
	private MapPanel mapPanel;

	public Scenario() {
		if (instance != null) {
			throw new IllegalStateException("Should be only one scenario!");
		}
		instance = this;
		pois = new ArrayList<>();
		factions = new ArrayList<>();
		systems = new ArrayList<>();
		actions = new ArrayList<>();
		animationRequest = new ArrayList<>();
	}

	public void add(BasePoi poi) {
		pois.add(poi);
	}

	public List<BasePoi> getPois() {
		return pois;
	}

	public List<Faction> getFactions() {
		return factions;
	}

	public Faction findFaction(FactionEnum type) {
		return factions.stream().filter(f -> f.getType().equals(type)).findFirst().get();
	}

	public Faction findFaction(String name) {
		return factions.stream().filter(f -> f.getName().equals(name)).findFirst().get();
	}

	public void add(Faction faction) {
		factions.add(faction);
	}

	public List<GameSystem> getSystems() {
		return systems;
	}

	public void add(GameSystem system) {
		systems.add(system);
	}

	public void turn() {
		turnCounter++;
		if (getAncient().getPower() >= 5) {
			// game end, win
			MapPanel.displayEvent(Msg.getText(Msg.WIN_MSG));
		}
		if (getAwarness() >= 5) {
			// game end, lose
			MapPanel.displayEvent(Msg.getText(Msg.LOSE_MSG));
		}
		for (Poi poi : pois) {
			if (poi instanceof CityPoi) {
				((CityPoi) poi).turn();
			}
			if (poi instanceof RuinPoi) {
				((RuinPoi) poi).turn();
			}
		}
		for (GameSystem gs : systems) {
			gs.act(this);
		}
		for (Action a : actions) {
			a.act();
		}
		List<Action> toRemove = actions.stream().filter(a -> a.isFinished()).collect(Collectors.toList());
		if (toRemove != null && !toRemove.isEmpty()) {
			toRemove.stream().filter(a -> a.getActor() != null).forEach(a -> a.getActor().setDoingAction(false));
			long c = toRemove.stream().filter(a -> a.getType().isPlayerAction()).count();
			if (c > 0) {
				MapPanel.appendLog("", "Renew ancient power " + c);
				getAncient().addPower(c);
			}
		}
		actions.removeAll(toRemove);
		toRemove = actions.stream().filter(a -> a.getActor() == null || a.getActor().getHealth() < 0)
				.collect(Collectors.toList());
		actions.removeAll(toRemove);
		// run animations
		for (AnimationRequest ar : animationRequest) {
			makeAnimation(ar);
			moveActor(ar);
		}
		animationRequest.clear();
	}

	private void moveActor(AnimationRequest ar) {
		Actor png = ar.getPng();
		ar.getFrom().removeActor(png);
		ar.getTarget().addActor(png);
	}

	private void makeAnimation(AnimationRequest ar) {
		try {
			mapPanel.addPngIcon(ar.getPng(), ar.getFrom(), ar.getTarget());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setAncient(Ancient ancient) {
		this.ancient = ancient;
	}

	public Ancient getAncient() {
		return ancient;
	}

	public String getTooltip(ActionEnum actionName) {
		switch (actionName) {
		case MANIPULATE:
			return "Manipulate\nManipulate target in following his darkest desires\n["
					+ getAncient().getCost(ActionEnum.MANIPULATE) + " turns]";
		case CORRUPT:
			return "Corrupt\nCorrupt target character, that become one of your puppets\n["
					+ getAncient().getCost(ActionEnum.CORRUPT) + " turns]";
		case CALLING:
			return "Calling\nForce target to investigate about hidden secrets\n["
					+ getAncient().getCost(ActionEnum.CORRUPT) + " turns]";
		case MOVING:
			return "Move\nHuman puppets can be forced to go anywhere\n[" + getAncient().getCost(ActionEnum.MOVING)
					+ " turns]";

		default:
			break;
		}
		return "";
	}

	public void act(ActionEnum action, Actor from, Actor to, Poi poi) {
		switch (action) {
		case CORRUPT:
			Action a = ActionBuilder.buildCorrupt(from, ancient.getCost(ActionEnum.CORRUPT), poi);
			from.setActionCounter(a.getTime());
			from.setDoingAction(true);
			from.setCurrenctActionType(a.getType());
			getAncient().pay();
			actions.add(a);
			break;
		case CALLING:
			Action b = ActionBuilder.buildCalling(from, ancient.getCost(ActionEnum.CALLING), poi);
			from.setActionCounter(b.getTime());
			from.setDoingAction(true);
			from.setCurrenctActionType(b.getType());
			getAncient().pay();
			actions.add(b);
			break;
		case MANIPULATE:
			Action c = ActionBuilder.buildManipulate(this, from, ancient.getCost(ActionEnum.MANIPULATE), to, poi);
			from.setActionCounter(c.getTime());
			from.setCurrenctActionType(c.getType());
			from.setDoingAction(true);
			getAncient().pay();
			actions.add(c);
			break;
		case INVESTIGATE:
			Action d = ActionBuilder.buildInvestigate(this, from, poi, 10);
			from.setActionCounter(d.getTime());
			from.setCurrenctActionType(d.getType());
			from.setDoingAction(true);
			actions.add(d);
			break;
//		case MOVE_INVESTIGATE:
//			Action d = ActionBuilder.buildInvestigate(this, from, poi, 10);
//			from.setActionCounter(d.getTime());
//			from.setCurrenctActionType(d.getType());
//			from.setDoingAction(true);
//			actions.add(d);
//			break;
		default:
			break;
		}
	}

	public static Scenario getInstance() {
		return instance;
	}

	public void unlockSecret(Poi poi, Actor actor, BaseSecret secret) {
		District district = poi.getDistricts().stream().filter(d -> d.nome.equals(secret.getDistrict())).findFirst()
				.get();
		district.secretAvailable = true;
	}

	public void removeActorFromPoi(String name) {
		Actor toRemove = null;
		for (Poi poi : getPois()) {
			for (Actor actor : poi.getActors()) {
				if (actor.getName().equals(name)) {
					toRemove = actor;
				}
			}
			poi.getActors().remove(toRemove);
		}
		// remove from relations
		for (Poi poi : getPois()) {
			Iterator<Actor> actorIter = poi.getActors().iterator();
			while (actorIter.hasNext()) {
				Actor actor = actorIter.next();
				Iterator<Entry<Actor, Relationship>> iter = actor.getRelations().iterator();
				while (iter.hasNext()) {
					Entry<Actor, Relationship> e = iter.next();
					if (e.getKey().equals(toRemove)) {
						iter.remove();
						// actor.getRelations().remove(e);
					}
				}
				if (actor.getRelations().isEmpty()) {
					// if actor is without relation add another random
					addRandomRelation(actor, poi.getActors(), toRemove);
				}
			}
		}
		// remove from districts

		// TODO: removed from poi, why? tip: from manipulate action ? could
		// spawn some turns later in another city with a big clue ?
		// TODO: if after a kill, could trigger more clues e/o events ?
	}

	private void addRandomRelation(Actor actor, final List<Actor> collect, Actor toRemove) {
		List<Actor> temp = new ArrayList<>(collect);
		temp.remove(actor);
		temp.remove(toRemove);
		// see ScenarioBuilder
		// TODO: need for RelationBuilder ?
		if (!temp.isEmpty()) {
			actor.putRelation(temp.get(rnd.nextInt(temp.size())), Relationship.HATE);
		} else {
			// TODO: is empty, need to recruit ?
		}
	}

	public String combat(Actor from, Actor target) {
		String res = "";
		// TODO: first implementation: only role dictate combat
		switch (from.getRole()) {
		case WARRIOR:
		case ASSASSIN:
		case MERCHANT:
			res = normalCombat(from, target);
			break;
		case MAGE:
			res = arcaneCombat(from, target);
			break;

		default:
			break;
		}
		return res;
	}

	private String arcaneCombat(Actor from, Actor target) {
		String res = "";
		target.addHealth(-from.getArcane());
		if (target.getHealth() <= 0) {
			res = "Using arcane powers " + from.getName() + " kills " + target.getName();
			removeFromSimulation(target);
			removeActorFromPoi(target.getName());
			return res;
		}
		from.addHealth(-target.getArcane());
		if (from.getHealth() <= 0) {
			res = "Using arcane powers " + target.getName() + " kills " + from.getName();
			removeFromSimulation(from);
			removeActorFromPoi(from.getName());
			return res;
		}
		res = "Surprisingly for " + from.getName() + ", " + target.getName() + " defend and is still alive";
		return res;
	}

	private String normalCombat(Actor from, Actor target) {
		String res = "";
		target.addHealth(-from.getAttack());
		if (target.getHealth() <= 0) {
			res = "In a close fight " + from.getName() + " kills " + target.getName();
			removeActorFromPoi(target.getName());
			removeFromSimulation(target);
			return res;
		}
		from.addHealth(-target.getAttack());
		if (from.getHealth() <= 0) {
			res = "In a close fight " + target.getName() + " kills " + from.getName();
			removeActorFromPoi(from.getName());
			removeFromSimulation(from);
			return res;
		}
		res = "Surprisingly for " + from.getName() + ", " + target.getName() + " defend and is still alive";
		return res;
	}

	private void removeFromSimulation(Actor target) {
		if (target == null) {
			return;
		}
		for (BasePoi poi : pois) {
			if (poi.getActors() != null && !poi.getActors().isEmpty()) {
				if (poi.getActors().stream().anyMatch(a -> a.getName().equals(target.getName()))) {
					if (poi.getType().equals(PoiType.CITY)) {
						CityPoi p = (CityPoi) poi;
						Actor actor = p.getSimulation().find(target.getName());
						p.getSimulation().removePlayer(actor);
					}
				}
			}
		}
	}

	public int armyFight(Stats rebellionStats, Stats armyStats) {
		armyStats.health -= rebellionStats.attack;
		if (armyStats.health <= 0) {
			return 1;
		}
		rebellionStats.health -= armyStats.attack;
		if (rebellionStats.health <= 0) {
			return -1;
		}
		return 0;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public void setAwarness(int awarness) {
		this.awarness = awarness;
	}

	public void addAwarness(int n) {
		this.awarness += n;
	}

	public int getAwarness() {
		return awarness;
	}

	public ActionEnum getCurrentAction(Actor actor) {
		for (Action action : actions) {
			if (action.getActor().getName().equals(actor.getName())) {
				return action.getType();
			}
		}
		return ActionEnum.IDLE;
	}

	public void makePeace(Faction faction, String otherFaction) {
		Faction other = findFaction(otherFaction);
		if (faction.getStatus() == FactionStatusEnum.WAR) {
			faction.setStatus(FactionStatusEnum.PEACE);
			faction.setTarget(null);
			other.setStatus(FactionStatusEnum.PEACE);
			other.setTarget(null);
		}

	}

	public void makeWar(Faction faction, String otherFaction) {
		Faction other = findFaction(otherFaction);
		if (faction.getStatus() == FactionStatusEnum.PEACE) {
			faction.setStatus(FactionStatusEnum.WAR);
			faction.setTarget(other.getName());
			other.setStatus(FactionStatusEnum.WAR);
			other.setTarget(faction.getName());
		}
	}

	public boolean isCalling(Poi poi) {
		return actions.stream()
				.filter(a -> a.getType() == ActionEnum.CALLING && a.getPoi().getName().equals(poi.getName()))
				.findFirst().isPresent();
	}

	public boolean isUnlocked(Poi poi, BaseSecret secret) {
		District district = poi.getDistricts().stream().filter(d -> d.nome.equals(secret.getDistrict())).findFirst()
				.get();
		return district.secretAvailable;
	}

	public void removeSecret(Poi poi, BaseSecret secret) {
		poi.getSecrets().removeIf(s -> s.getName().equals(secret.getName()));
		District district = poi.getDistricts().stream().filter(d -> d.nome.equals(secret.getDistrict())).findFirst()
				.get();
		district.secretAvailable = false;
	}

	public Actor getRandomActor(CityPoi removedCity) {
		List<BasePoi> potentials = pois.stream().filter(p -> p.getType().equals(PoiType.CITY))
				.filter(c -> !c.getName().equals(removedCity.getName())).collect(Collectors.toList());
		CityPoi city = (CityPoi) potentials.get(rnd.nextInt(potentials.size()));
		return city.getActors().get(rnd.nextInt(city.getActors().size()));
	}

	public CityPoi getRandomCity(CityPoi removedCity) {
		List<BasePoi> potentials = pois.stream().filter(p -> p.getType().equals(PoiType.CITY))
				.filter(c -> !c.getName().equals(removedCity.getName())).collect(Collectors.toList());
		return (CityPoi) potentials.get(rnd.nextInt(potentials.size()));
	}

	public CityPoi findCity(String name) {
		return (CityPoi) pois.stream().filter(p -> p.getType().equals(PoiType.CITY))
				.filter(p -> p.getName().equals(name)).findFirst().get();
	}

	public BasePoi findPoi(String name) {
		return (BasePoi) pois.stream().filter(p -> p.getName().equals(name)).findFirst().get();
	}

	public void addAnimation(Actor png, BasePoi from, BasePoi target) {
		animationRequest.add(new AnimationRequest(png, from, target));
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public BasePoi findMoveablePoi(String name) {
		BasePoi current = findPoi(name);
		List<BasePoi> candidates = pois.stream().filter(CityPoi.class::isInstance).filter(p -> !p.isHidden())
				.filter(p -> current.getConnection().contains(p.getName())).collect(Collectors.toList());
		return candidates.get(rnd.nextInt(candidates.size()));
	}

}
