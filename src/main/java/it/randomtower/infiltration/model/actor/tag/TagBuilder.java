package it.randomtower.infiltration.model.actor.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.randomtower.infiltration.ChallengeType;
import it.randomtower.infiltration.Main;

public final class TagBuilder {

	private static List<Tag> tags;
	private static Random rnd = new Random();

	private static void load() {
		if (tags != null) {
			return;
		}
		ClassLoader classLoader = Main.class.getClassLoader();
		InputStream stream = classLoader.getResourceAsStream("tags.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			tags = mapper.readValue(stream, new TypeReference<List<Tag>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static final List<Tag> buildRandom(int n) {
		load();
		List<Tag> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (rnd.nextFloat() < 0.40f) {
			}
			int r = rnd.nextInt(tags.size());
			res.add(tags.get(r));
		}
		return res;
	}

	public static Tag buildAncientPower(int powerLevel, TagEffect effect, ChallengeType type) {
		return new Tag("ancient power",
				"more powerful is the ancient, more influence on the mortals\n+20% every ancient one power", effect,
				true, type, powerLevel * 0.2f);
	}

	public static Tag find(String rewardTag) {
		if (rewardTag == null) {
			return null;
		}
		return tags.stream().filter(t -> t.getName().equals(rewardTag)).findFirst().get();
	}

}
