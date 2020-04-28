package it.randomtower.infiltration.model.faction;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.randomtower.infiltration.Main;

public final class FactionBuilder {

	public static final String THIEF_GUILD = "Thief Guild";
	public static final String MAGE_GUILD = "Mage Guild";
	public static final String MERCHANT_GUILD = "Merchant Guild";
	public static final String WARRIOR_GUILD = "Warrior Guild";
	public static final String ASSASSIN_GUILD = "Assassin Guild";
	public static final String MONSTER_FACTION = "Monsters";
	public static final String factions[] = { THIEF_GUILD, MAGE_GUILD, MERCHANT_GUILD, WARRIOR_GUILD, ASSASSIN_GUILD,
			MONSTER_FACTION };

	private static BaseFaction build(String name, String description) {
		BaseFaction faction = new BaseFaction();
		faction.setName(name);
		faction.setDescription(description);
		faction.setRelations(buildBaseRelation(name));
		faction.setStatus(FactionStatusEnum.PEACE);
		return faction;
	}

	public static BaseFaction buildWarriorGuild() {
		BaseFaction faction = build(WARRIOR_GUILD, "A mercenary group that can fight to the hightest bidder");
		faction.setImage(load("Icon.2_43.jpg"));
		faction.setImageRoll(load("Icon.2_43.jpg"));
		faction.setType(FactionEnum.WARRIOR);
		return faction;
	}

	public static BaseFaction buildMerchantGuild() {
		BaseFaction faction = build(MERCHANT_GUILD, "A cartel of merchants that control part of the economy");
		faction.setImage(load("Icons8_52.jpg"));
		faction.setImageRoll(load("Icons8_52.jpg"));
		faction.setType(FactionEnum.MERCHANT);
		return faction;
	}

	public static BaseFaction buildMageGuild() {
		BaseFaction faction = build(MAGE_GUILD, "A group of scholars dedicated to arcane and forgotten knowledges");
		faction.setImage(load("Icon.4_19.jpg"));
		faction.setImageRoll(load("Icon.4_19.jpg"));
		faction.setType(FactionEnum.MAGE);
		return faction;
	}

	public static BaseFaction buildThiefGuild() {
		BaseFaction faction = build(THIEF_GUILD, "Organized crime society forged by sacred deals");
		faction.setImage(load("Icons8_21.jpg"));
		faction.setImageRoll(load("Icons8_21.jpg"));
		faction.setType(FactionEnum.THIEF);
		return faction;
	}

	public static BaseFaction buildAssassinGuild() {
		BaseFaction faction = build(ASSASSIN_GUILD,
				"Not a normal organization, but a group of individuals that kill for money");
		faction.setImage(load("Icon.1_68.jpg"));
		faction.setImageRoll(load("Icon.1_68.jpg"));
		faction.setType(FactionEnum.ASSASSIN);
		return faction;
	}

	public static BaseFaction buildMonsterFaction() {
		BaseFaction faction = build(MONSTER_FACTION, "Monsters with nothing in common with humanity");
		faction.setImage(load("Icon.1_68.jpg"));
		faction.setImageRoll(load("Icon.1_68.jpg"));
		faction.setType(FactionEnum.MONSTERS);
		return faction;
	}

	private static BufferedImage load(String name) {
		try {
			return Main.getFileResource(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Map<String, Integer> buildBaseRelation(String name) {
		Map<String, Integer> relation = new HashMap<>();
		List<String> fs = new ArrayList<String>(Arrays.asList(factions));
		fs.remove(name);
		for (String fact : fs) {
			relation.put(fact, -20);
		}
		return relation;
	}

}
