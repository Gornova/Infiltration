package it.randomtower.infiltration.citysimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import it.randomtower.infiltration.model.actor.Actor;

public class CitySimulation implements Simulation {

	private static final int MAX_SEGUACI = 30;
	private List<Actor> giocatori = new ArrayList<>();
	private List<District> luoghi = new ArrayList<>();
	private List<Quest> quests = new ArrayList<>();
	public List<Miglioramento> miglioramenti = new ArrayList<>();
	private String name;
	private Random rnd = new Random();

	public CitySimulation(String name) {
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
		Collections.sort(luoghi);
		turn();
	}

	private void turn() {
		for (Actor png : giocatori) {
			// System.out.println(png.toString());
			// bonus passivi
			png.addMoney(1);
			if (png.getFollowersBonus() > 0 && png.getFollowers() + png.getFollowersBonus() <= MAX_SEGUACI) {
				png.addFollowers(png.getFollowersBonus());
			}
			if (png.getMoneyBonus() > 0) {
				png.addMoney(png.getMoneyBonus());
			}
			Azione azione = decidiAzione(png);
			azione.esegui(this, png);
		}
		if (luoghi.stream().filter(l -> !l.padrone.isEmpty()).count() == luoghi.size()) {
			Map<String, String> map = luoghi.stream().collect(Collectors.toMap(l -> l.nome, l -> l.padrone));
			if (map.values().stream().distinct().count() == 1) {
				// TODO: what if an actor "win" the city ?
				String winner = map.values().stream().distinct().findFirst().get();
			}
		}
	}

	private Azione decidiAzione(Actor png) {
		if (true)
			if (png.getArcane() > 0) {
				Collections.shuffle(miglioramenti);
				for (Miglioramento miglioramento : miglioramenti) {
					if (png.getArcane() >= miglioramento.costo) {
						switch (miglioramento.type) {
						case SEGUACI:
							return new AzioneMiglioraSeguaci();
						case DENARO:
							return new AzioneMiglioraDenaro();
						default:
							break;
						}
					}
				}
			}
		Collections.shuffle(quests);
		for (Quest q : quests) {
			if (isValidPlaceQuest(q, png)) {
				if (png.getMoney() >= q.cost && png.getFollowers() >= q.followerCost) {
					AzioneEseguiQuest azione = new AzioneEseguiQuest();
					azione.setQuest(q);
					return azione;
				}
			}
			if (png.getMoney() >= q.cost && png.getFollowers() >= q.followerCost) {
				AzioneEseguiQuest azione = new AzioneEseguiQuest();
				azione.setQuest(q);
				return azione;
			}
		}
		Collections.shuffle(luoghi);
		for (District luogo : luoghi) {
			if (png.getInfluence() >= luogo.influenza && luogo.padrone.isEmpty()) {
				AzioneAccessoStruttura azione = new AzioneAccessoStruttura();
				azione.setPlace(luogo);
				return azione;
			}
			if (!luogo.padrone.isEmpty()) {
				// attacco coi seguaci ?
				Actor altro = find(luogo.padrone);
				// non sono io
				if (!altro.getName().equals(png.getName())) {
					if (altro.getFollowers() + luogo.difesa <= png.getFollowers()) {
						AzioneAttaccaStruttura azione = new AzioneAttaccaStruttura();
						azione.setPlace(luogo);
						return azione;
					}
				}
			}
		}
		if (png.getMoney() >= 2 && !ownAnything(png) && !png.isCorrupted() && rnd.nextBoolean()) {
			return new AzioneCambioPoi();
		}
		if (png.getMoney() >= 2 && png.getFollowers() <= MAX_SEGUACI) {
			return new AzioneReclutaSeguaci();
		}

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
