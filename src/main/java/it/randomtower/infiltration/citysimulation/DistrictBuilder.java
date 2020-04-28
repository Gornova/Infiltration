package it.randomtower.infiltration.citysimulation;

import java.util.ArrayList;
import java.util.List;

public final class DistrictBuilder {

	public static final String CATHEDRAL = "cathedral";
	public static final String MARKET = "market";
	public static final String CASTLE = "castle";
	public static final String DOCKS = "docks";
	public static final String CAVE = "cave";

	public static District buildDock() {
		District porto = new District();
		porto.nome = DOCKS;
		porto.difesa = 4;
		porto.influenza = 2;
		return porto;
	}

	public static District buildCastle() {
		District castello = new District();
		castello.nome = CASTLE;
		castello.difesa = 5;
		castello.influenza = 5;
		return castello;
	}

	public static District buildMarket() {
		District mercato = new District();
		mercato.nome = MARKET;
		mercato.difesa = 2;
		mercato.influenza = 4;
		return mercato;
	}

	public static District buildCathedral() {
		District cattedrale = new District();
		cattedrale.nome = CATHEDRAL;
		cattedrale.difesa = 3;
		cattedrale.influenza = 3;
		return cattedrale;
	}

	public static List<District> build() {
		List<District> result = new ArrayList<>();

		result.add(DistrictBuilder.buildDock());
		result.add(DistrictBuilder.buildCastle());
		result.add(DistrictBuilder.buildMarket());
		result.add(DistrictBuilder.buildCathedral());

		return result;
	}

	public static List<District> buildRuin() {
		List<District> result = new ArrayList<>();
		result.add(DistrictBuilder.buildCave());
		return result;
	}

	public static District buildCave() {
		District cave = new District();
		cave.nome = CAVE;
		cave.difesa = 0;
		cave.influenza = 0;
		return cave;
	}

}
