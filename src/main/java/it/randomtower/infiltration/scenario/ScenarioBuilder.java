package it.randomtower.infiltration.scenario;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.ActorBuilder;
import it.randomtower.infiltration.model.actor.Relationship;
import it.randomtower.infiltration.model.ancient.Ancient;
import it.randomtower.infiltration.model.faction.BaseFaction;
import it.randomtower.infiltration.model.faction.FactionBuilder;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.PoiBuilder;
import it.randomtower.infiltration.model.poi.RuinPoi;
import it.randomtower.infiltration.model.poi.Secret;
import it.randomtower.infiltration.model.poi.SecretBuilder;
import it.randomtower.infiltration.model.poi.StatusEnum;
import it.randomtower.infiltration.system.ClueSystem;
import it.randomtower.infiltration.system.FactionSystem;
import it.randomtower.infiltration.system.FoodSystem;
import it.randomtower.infiltration.system.RebellionSystem;
import it.randomtower.infiltration.system.RecruitmentSystem;

public final class ScenarioBuilder {

	private static Random rnd = new Random();

	private static BaseFaction warriorGuild;

	private static BaseFaction merchantGuild;

	private static BaseFaction mageGuild;

	private static BaseFaction thiefGuild;

	private static BaseFaction assassinGuild;

	private static BaseFaction monsterFaction;

	private static List<PoiJson> pois;

	private static void load() {
		if (pois != null) {
			return;
		}
		ClassLoader classLoader = Main.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("pois.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			pois = mapper.readValue(stream, new TypeReference<List<PoiJson>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final Scenario buildDemo() throws IOException {
		load();
		Scenario scenario = new Scenario();

		Ancient ancient = new Ancient();
		ancient.setPower(1);
		scenario.setAncient(ancient);

		scenario.add(new FoodSystem());
		scenario.add(new RebellionSystem());
		scenario.add(new ClueSystem());
		scenario.add(new RecruitmentSystem(0.02f));
		scenario.add(new FactionSystem());

		warriorGuild = FactionBuilder.buildWarriorGuild();
		scenario.add(warriorGuild);
		merchantGuild = FactionBuilder.buildMerchantGuild();
		scenario.add(merchantGuild);
		mageGuild = FactionBuilder.buildMageGuild();
		scenario.add(mageGuild);
		thiefGuild = FactionBuilder.buildThiefGuild();
		scenario.add(thiefGuild);
		assassinGuild = FactionBuilder.buildAssassinGuild();
		scenario.add(assassinGuild);
		monsterFaction = FactionBuilder.buildMonsterFaction();
		scenario.add(monsterFaction);

		for (PoiJson poi : pois) {
			if (poi.getType().equals("CITY")) {
				CityPoi cityPoi = PoiBuilder.buildCity(poi.getName(), poi.getDescription(), poi.getNickname(),
						poi.getX(), poi.getY(), StatusEnum.valueOf(poi.getStatus()), poi.getFood(), poi.getPopulation(),
						poi.getWork(), poi.getMoney(), poi.getConnections());
				cityPoi.setImage(Main.getFileResource(poi.getImage()));
				cityPoi.setImageRoll(Main.getFileResource(poi.getImageRoll()));
				cityPoi.setImageRebellion(Main.getFileResource(poi.getImageRebellion()));
				buildActors(cityPoi, rndCharNumber(4) + 3, scenario);
				if (poi.getSecret() != null & poi.getSecret()) {
					addSecret(cityPoi);
				}
				scenario.add(cityPoi);
			}
			if (poi.getType().equals("RUIN")) {
				RuinPoi ruinPoi = PoiBuilder.buildRuin(poi.getName(), poi.getDescription(), poi.getNickname(),
						poi.getX(), poi.getY(), poi.getConnections(), poi.getHidden());
				ruinPoi.setImage(Main.getFileResource(poi.getImage()));
				ruinPoi.setImageRoll(Main.getFileResource(poi.getImageRoll()));
				ruinPoi.setImageRebellion(Main.getFileResource(poi.getImageRebellion()));
				ruinPoi.addActor(ActorBuilder.buildRandomMonster(scenario));
//				ruinPoi.addActor(ActorBuilder.buildZombie(scenario));
//				ruinPoi.addActor(ActorBuilder.buildSkeleton(scenario));
//				ruinPoi.addActor(ActorBuilder.buildGhost(scenario));
//				ruinPoi.addActor(ActorBuilder.buildAbomination(scenario));
				if (poi.getSecret() != null && poi.getSecret()) {
					addSecret(ruinPoi);
				}
				scenario.add(ruinPoi);
			}

		}
		return scenario;
	}

	private static int rndCharNumber(int max) {
		return rnd.nextInt(max);
	}

	private static void buildActors(CityPoi city, int n, Scenario scenario) {
		// add n actor for city
		for (int i = 0; i < n; i++) {
			Actor actor = ActorBuilder.buildRandomWithFaction(scenario);
			city.addActor(actor);
		}
		buildRandomHate(city);
	}

	private static void addSecret(BasePoi poi) {
		Secret secret = SecretBuilder.buildBase(getRandomDistrict(poi));
		poi.add(secret);
	}

	private static String getRandomDistrict(BasePoi poi) {
		int i = rnd.nextInt(poi.getDistricts().size());
		return poi.getDistricts().get(i).nome;
	}

	private static void buildRandomHate(CityPoi city) {
		for (Actor first : city.getActors()) {
			if (city.getActors().size() == 1) {
				throw new IllegalArgumentException("No relation with one actor only");
			} else if (city.getActors().size() == 2) {
				buildHateRelationship(first, city.getActors().get(1));
				buildHateRelationship(city.getActors().get(1), first);
				return;
			}
			int hateCountTarget = rnd.nextInt(city.getActors().size()) + 1;
			int hateCount = 0;
			for (int i = 0; i < city.getActors().size(); i++) {
				Actor second = city.getActors().get(i);
				if (hateCount < hateCountTarget && !first.getName().equals(second.getName())) {
					if (rnd.nextBoolean()) {
						hateCount++;
						buildHateRelationship(first, second);
					}
				}
				if (hateCount < hateCountTarget && i + 1 >= city.getActors().size()) {
					// hate last
					if (!first.getName().equals(second.getName())) {
						buildHateRelationship(first, second);
						hateCount++;
					} else {
						Actor other = city.getActors().get(i - 1);
						buildHateRelationship(first, other);
						hateCount++;
					}
				}
			}
		}
	}

	private static void buildHateRelationship(Actor first, Actor second) {
		first.putRelation(second, Relationship.HATE);
	}

}
