package it.randomtower.infiltration.citysimulation;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.randomtower.infiltration.Main;

public final class QuestBuilder {

	public static List<Quest> buildQuests() {
		try {
			List<Quest> result = new ArrayList<>();
			ClassLoader classLoader = Main.class.getClassLoader();
			InputStream stream = classLoader.getResourceAsStream("quests.json");
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(stream, new TypeReference<List<Quest>>() {
			});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
