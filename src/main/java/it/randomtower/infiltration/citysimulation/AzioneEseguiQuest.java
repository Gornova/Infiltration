package it.randomtower.infiltration.citysimulation;

import java.util.Random;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.tag.TagBuilder;
import it.randomtower.infiltration.ui.map.MapPanel;

public class AzioneEseguiQuest implements Azione {

	private boolean finita = false;
	private Quest quest;
	private Random rnd = new Random();

	public void setQuest(Quest quest) {
		this.quest = quest;
	}

	@Override
	public void esegui(Simulation game, Actor png) {
		if (png.getMoney() < costoDenaro() || png.getFollowers() < costoSeguaci()) {
			return;
		}
		// check challenge: roll 1d100 (+ bonus or - malus for given type of
		// challenge against tag) against base difficulty, if more, quest solved
		float challenge = rnd.nextFloat() + getTagValue(png, quest);
		if (challenge >= (quest.difficulty / 100f)) {
			String log = "";
			png.addMoney(-quest.cost);
			png.addFollowers(-quest.followerCost);
			png.addInfluence(+quest.influenceGain);
			if (quest.arcaneGain >= 0) {
				png.addArcane(+quest.arcaneGain);
			}
			if (quest.rewardTag != null && !quest.rewardTag.isEmpty()) {
				png.addTag(TagBuilder.find(quest.rewardTag));
				log = String.format(Msg.getText(Msg.ACTION_SOLVE_QUEST), png.getName(), costoDenaro(), costoSeguaci(),
						quest.name, quest.influenceGain, quest.arcaneGain, quest.rewardTag);
			} else {
				log = String.format(Msg.getText(Msg.ACTION_SOLVE_QUEST), png.getName(), costoDenaro(), costoSeguaci(),
						quest.name, quest.influenceGain, quest.arcaneGain);
			}

			MapPanel.appendLog(game.getName(), log);
			png.addQuestLog(log);

			finita = true;
			// rimuovi quest
			game.remove(quest);
		} else {
			String log = "";
			if (quest.loseTag != null && !quest.loseTag.isEmpty()) {
				png.addTag(TagBuilder.find(quest.rewardTag));
				log = String.format(Msg.getText(Msg.ACTION_SOLVE_QUEST_NO_LUCK), png.getName(), quest.name,
						quest.loseTag);
			} else {
				log = String.format(Msg.getText(Msg.ACTION_SOLVE_QUEST_NO_LUCK), png.getName(), quest.name);
			}

			MapPanel.appendLog(game.getName(), log);
			png.addQuestLog(log);
		}
	}

	private float getTagValue(Actor png, Quest quest) {
		if (png == null) {
			return 0;
		}
		float value = 0;
		value = (float) png.getTags().stream().filter(t -> t.getChallengeType().equals(quest.type))
				.mapToDouble(t -> t.getValue()).sum();
		return value;
	}

	@Override
	public int costoDenaro() {
		return quest.cost;
	}

	@Override
	public int costoInfluenza() {
		return -1;
	}

	@Override
	public boolean completata() {
		return finita;
	}

	@Override
	public int costoSeguaci() {
		return quest.followerCost;
	}

	@Override
	public int costoArcano() {
		return 0;
	}

}
