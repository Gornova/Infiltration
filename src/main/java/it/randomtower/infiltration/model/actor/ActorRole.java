package it.randomtower.infiltration.model.actor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum ActorRole {

	MERCHANT, MAGE, WARRIOR, THIEF, ASSASSIN, MONSTER;

	public static ActorRole getRandom() {
		Random rnd = new Random();
		List<ActorRole> candidates = Arrays.asList(values()).stream().filter(v -> !v.equals(MONSTER))
				.collect(Collectors.toList());
		return candidates.get(rnd.nextInt(candidates.size()));
	}

}
