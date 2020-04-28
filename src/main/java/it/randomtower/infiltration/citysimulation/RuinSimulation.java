package it.randomtower.infiltration.citysimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.model.actor.Actor;

public class RuinSimulation implements Simulation {

	private static final int MAX_SEGUACI = 30;
	private List<Actor> giocatori = new ArrayList<>();
	private List<District> luoghi = new ArrayList<>();
	private List<Quest> quests = new ArrayList<>();
	public List<Miglioramento> miglioramenti = new ArrayList<>();
	private String name;
	private Random rnd = new Random();

	public RuinSimulation(String name) {
		this.name = name;
	}

	public void addPlayer(Actor giocatore) {
		this.giocatori.add(giocatore);
	}

	public void removePlayer(Actor player) {
		this.giocatori.remove(player);
		this.luoghi.forEach(d -> {
			if (d.padrone.equals(player.getName())) {
				d.padrone = "";
			}
		});
	}

	public void addQuests(List<Quest> quests) {
		this.quests.addAll(quests);
	}

	public void run() {
		turn();
	}

	private void turn() {
		for (Actor png : giocatori) {
			// System.out.println(png.toString());
			// bonus passivi
			Azione azione = decidiAzione(png);
			azione.esegui(this, png);
		}
	}

	private Azione decidiAzione(Actor png) {
		return new AzionePasso();
	}

	private boolean ownAnything(Actor png) {
		return luoghi.stream().filter(l -> l.padrone != null && l.padrone.equals(png.getName())).count() > 0;
	}

	private boolean isValidPlaceQuest(Quest q, Actor png) {
		List<String> strutturePossedute = findPlacesFor(png.getName());
		if (!strutturePossedute.isEmpty() && !q.place.isEmpty()) {
			if (strutturePossedute.contains(q.place)) {
				return true;
			}
		}
		return false;
	}

	public Actor find(String nome) {
		return giocatori.stream().filter(g -> g.getName().equals(nome)).findFirst().get();
	}

	public List<String> findPlacesFor(String nomePng) {
		return luoghi.stream().filter(l -> !l.padrone.isEmpty() && l.padrone.equals(nomePng)).map(l -> l.nome)
				.collect(Collectors.toList());
	}

	public void remove(Quest quest2) {
		this.quests.remove(quest2);
	}

	public void addDistricts(List<District> build) {
		this.luoghi.addAll(build);
	}

	public List<District> getDistricts() {
		return luoghi;
	}

	public String getName() {
		return name;
	}

}
