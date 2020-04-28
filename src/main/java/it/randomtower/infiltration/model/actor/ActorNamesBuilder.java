package it.randomtower.infiltration.model.actor;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.randomtower.infiltration.Main;

public class ActorNamesBuilder {

	public List<String> merchantNames;
	// = Arrays.asList("Anduin", "Fidas", "Juakin", "Manilos", "Palin", "Zakar",
	// "Fulvius", "Malin", "Morakin", "Kilor", "Ranish", "Calindar", "Lanitir",
	// "Negus", "Jiulius", "Anton");
	public List<String> mageNames;
	// = Arrays.asList("Valerian", "Ixi", "Antonidas", "Sparviero", "Silent",
	// "Malikish",
	// "Seeker", "Valenidas", "Galindos", "Walenid", "Loundin", "Landrin",
	// "Kol-dar", "Mal-dar", "Ran-dar");
	public List<String> warriorNames;
	// = Arrays.asList("Uzbar", "Korgan", "Gravios", "Mantonius", "Edail",
	// "Krogash",
	// "Galos", "Acnos", "Maaros", "Jonis", "Erandos", "Randonis", "Parlonis",
	// "Darondin", "Walnos", "Hammerfall");
	public List<String> thiefNames;
	// = Arrays.asList("Randal", "The Marked", "Phil", "Golden hand",
	// "Vincenzo", "Rowe",
	// "Ols", "Xandos", "Nambor", "Lopas", "Loris", "Malindor", "Ruinseeker",
	// "Kal", "Bor", "Quan");
	public List<String> assassinNames;
	// = Arrays.asList("One eyed", "Jonathan", "Brankle", "Black hand", "White
	// hand",
	// "Oils", "Vanidor", "Black", "Tandorin", "Daron", "Phalos", "Phebe",
	// "Malixin", "Xin-do", "Mankor-sa",
	// "Zalon");

	public static ActorNamesBuilder buildActorNames() {
		try {
			ActorNamesBuilder result;
			ClassLoader classLoader = Main.class.getClassLoader();
			InputStream stream = classLoader.getResourceAsStream("actorNames.json");
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(stream, new TypeReference<ActorNamesBuilder>() {
			});
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
