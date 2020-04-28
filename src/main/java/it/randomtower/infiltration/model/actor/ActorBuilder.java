package it.randomtower.infiltration.model.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import it.randomtower.infiltration.model.actor.tag.Tag;
import it.randomtower.infiltration.model.actor.tag.TagBuilder;
import it.randomtower.infiltration.model.faction.FactionEnum;
import it.randomtower.infiltration.scenario.Scenario;

public final class ActorBuilder {

	private static Random rnd = new Random();

	private static List<String> usedNames = new ArrayList<>();

	private static ActorNamesBuilder names = ActorNamesBuilder.buildActorNames();

	private static Actor build(String name, String description, Stats stats, ActorRole role) {
		Actor actor = new Actor();
		actor.setName(name);
		actor.setDescription(description);
		actor.setStats(stats);
		actor.setRole(role);
		List<Tag> tags = TagBuilder.buildRandom(2);
		tags.forEach(t -> actor.addTag(t));
		System.out.println("temp Actor: " + name + "tags:" + tags.toString());
		return actor;
	}

	public static Actor buildMerchant(String name, String description, Stats stats) {
		return build(name, description, stats, ActorRole.MERCHANT);
	}

	public static Actor buildWarrior(String name, String description, Stats stats) {
		return build(name, description, stats, ActorRole.WARRIOR);
	}

	public static Actor buildThief(String name, String description, Stats stats) {
		return build(name, description, stats, ActorRole.THIEF);
	}

	public static Actor buildMage(String name, String description, Stats stats) {
		return build(name, description, stats, ActorRole.MAGE);
	}

	public static Actor buildAssassin(String name, String description, Stats stats) {
		return build(name, description, stats, ActorRole.ASSASSIN);
	}

	public static Actor buildGhast() {
		Stats stats = new Stats();
		stats.arcane = 2;
		stats.attack = 2;
		stats.health = 2;
		stats.madness = 2;
		return build("ghast",
				"a gray sick and scabrous beast without nose and forehead, but a face so curiously human ", stats,
				ActorRole.WARRIOR);
	}

	public static Actor buildHorror(Scenario scenario) {
		Stats stats = new Stats();
		stats.arcane = 4;
		stats.attack = 3;
		stats.health = 3;
		stats.madness = 5;
		Actor actor = build("horror", "a presence of fear, too crazy to be seen by eyes", stats, ActorRole.MAGE);
		actor.setFaction(scenario.findFaction(FactionEnum.MONSTERS));
		return actor;
	}

	public static Actor buildZombie(Scenario scenario) {
		Stats stats = new Stats();
		stats.arcane = 1;
		stats.attack = 4;
		stats.health = 5;
		stats.madness = 10;
		Actor actor = build("zombie", "an undying creature, that still kills", stats, ActorRole.MONSTER);
		actor.setFaction(scenario.findFaction(FactionEnum.MONSTERS));
		return actor;
	}

	public static Actor buildSkeleton(Scenario scenario) {
		Stats stats = new Stats();
		stats.arcane = 1;
		stats.attack = 3;
		stats.health = 5;
		stats.madness = 10;
		Actor actor = build("skelton", "arcane powers keep this bonees together, for a long forgotten reason", stats,
				ActorRole.MONSTER);
		actor.setFaction(scenario.findFaction(FactionEnum.MONSTERS));
		return actor;
	}

	public static Actor buildGhost(Scenario scenario) {
		Stats stats = new Stats();
		stats.arcane = 1;
		stats.attack = 2;
		stats.health = 5;
		stats.madness = 10;
		Actor actor = build("ghost", "a soul wander on earth", stats, ActorRole.MONSTER);
		actor.setFaction(scenario.findFaction(FactionEnum.MONSTERS));
		return actor;
	}

	public static Actor buildAbomination(Scenario scenario) {
		Stats stats = new Stats();
		stats.arcane = 1;
		stats.attack = 8;
		stats.health = 5;
		stats.madness = 10;
		Actor actor = build("abomination",
				"a necromancer would call this perfection, normal people will call it an abomination", stats,
				ActorRole.MONSTER);
		actor.setFaction(scenario.findFaction(FactionEnum.MONSTERS));
		return actor;
	}

	public static Actor buildRandom() {
		ActorRole role = ActorRole.getRandom();
		String name = "";
		Actor actor = null;
		switch (role) {
		case MERCHANT:
			name = getName(ActorRole.MERCHANT);
			actor = ActorBuilder.buildMerchant(name, "A rich merchant", StatsBuilder.build(1, 3, 8, 0, 5));
			return actor;
		case WARRIOR:
			name = getName(ActorRole.WARRIOR);
			actor = ActorBuilder.buildWarrior(name, "An adventurer", StatsBuilder.build(3, 3, 1, 0, 0));
			return actor;
		case THIEF:
			name = getName(ActorRole.THIEF);
			actor = ActorBuilder.buildThief(name, "One of Thief guild affiliates", StatsBuilder.build(2, 2, 2, 0, 0));
			return actor;
		case MAGE:
			name = getName(ActorRole.MAGE);
			actor = ActorBuilder.buildMage(name, "Scholar of arcane powers", StatsBuilder.build(1, 1, 3, 3, 0));
			return actor;
		case ASSASSIN:
			name = getName(ActorRole.ASSASSIN);
			actor = ActorBuilder.buildAssassin(name, "A shadow of Assassin Guild", StatsBuilder.build(3, 1, 2, 0, 2));
			return actor;
		default:
			// System.out.println("Not supported role!");
			return null;
		}
	}

	private static String getName(ActorRole role) {
		switch (role) {
		case MERCHANT:
			return getRandomNotUsedValue(names.merchantNames);
		case WARRIOR:
			return getRandomNotUsedValue(names.warriorNames);
		case THIEF:
			return getRandomNotUsedValue(names.thiefNames);
		case MAGE:
			return getRandomNotUsedValue(names.mageNames);
		case ASSASSIN:
			return getRandomNotUsedValue(names.assassinNames);

		default:
			break;
		}
		return null;
	}

	private static String getRandomNotUsedValue(final List<String> names) {
		List<String> p = names.stream().filter(n -> !usedNames.contains(n)).collect(Collectors.toList());
		if (p.isEmpty()) {
			return getRandomName();
		}
		String t = p.get(rnd.nextInt(p.size()));
		usedNames.add(t);
		return t;
	}

	public static List<Actor> buildHorrors(int n, Scenario scenario) {
		List<Actor> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(buildHorror(scenario));
		}
		return result;
	}

	public static String getRandomName() {
		return StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(4, 10));
	}

	public static Actor buildRandomWithFaction(Scenario scenario) {
		Actor actor = buildRandom();
		switch (actor.getRole()) {
		case WARRIOR:
			actor.setFaction(scenario.findFaction(FactionEnum.WARRIOR));
			break;
		case MERCHANT:
			actor.setFaction(scenario.findFaction(FactionEnum.MERCHANT));
			break;
		case MAGE:
			actor.setFaction(scenario.findFaction(FactionEnum.MAGE));
			break;
		case THIEF:
			actor.setFaction(scenario.findFaction(FactionEnum.THIEF));
			break;
		case ASSASSIN:
			actor.setFaction(scenario.findFaction(FactionEnum.ASSASSIN));
			break;
		default:
			// System.out.println("Not supported role!!");
			return null;
		}
		return actor;
	}

	public static Actor buildRandomMonster(Scenario scenario) {
		int i = rnd.nextInt(4);
		switch (i) {
		case 0:
			return buildZombie(scenario);
		case 1:
			return buildSkeleton(scenario);
		case 2:
			return buildGhost(scenario);
		case 3:
			return buildAbomination(scenario);

		default:
			throw new IllegalArgumentException("cannot create a random monster with random value=" + i);
		}
	}

}
