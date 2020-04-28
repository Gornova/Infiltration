package it.randomtower.infiltration.model.action;

import java.util.List;
import java.util.Random;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.actor.ActorBuilder;
import it.randomtower.infiltration.model.faction.Faction;
import it.randomtower.infiltration.model.poi.StatusEnum;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class ManipulateAction extends BaseAction {

	private StringBuilder sb = new StringBuilder();
	private Scenario scenario;
	private Random rnd = new Random();

	public ManipulateAction(Scenario scenario) {
		setType(ActionEnum.MANIPULATE);
		this.scenario = scenario;
	}

	@Override
	public void act() {
		if (isFinished()) {
			return;
		}
		counter++;
		getActor().setActionCounter(getTime() - counter);
		if (counter >= turns) {
			setFinished(true);
			// current action
			MapPanel.appendLog(getPoi().getName(), String.format(Msg.getText(Msg.ACTION_MANIPULATE_COMPLETED),
					getActor().getName(), getTarget().getName()));
			String result = "";
			switch (getActor().getRole()) {
			case MERCHANT:
				result = buildMerchant();
				break;
			case MAGE:
				result = buildMage();
				break;
			case WARRIOR:
				result = buildWarrior();
				break;
			case THIEF:
				result = buildThief();
				break;
			case ASSASSIN:
				result = buildAssasin();
				break;

			default:
				break;
			}
			MapPanel.displayEvent(result);
			getPoi().addClue(3);
		}
	}

	private String buildMerchant() {
		if (getActor().getMadness() < 5 || getPoi().getStatus().equals(StatusEnum.REBELLION)) {
			getActor().setMoney(0);
			String log = String.format(Msg.getText(Msg.CORRUPTION_COMPLETED), getActor().getName(),
					getTarget().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getTarget().addMadness(3);
			scenario.removeActorFromPoi(getTarget().getName());
		} else {
			getActor().setMoney(0);
			getTarget().setMoney(0);
			getActor().setMadness(0);
			String log = String.format(Msg.getText(Msg.HIGH_TAXES), getActor().getName(), getTarget().getName(),
					getActor().getFaction().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getPoi().addRebellion(2);
			worseRelation(getActor().getFaction(), -10);
		}
		return sb.toString();
	}

	private void worseRelation(Faction faction, int value) {
		faction.getRelations().forEach((otherFaction, rel) -> {
			rel += value;
			faction.getRelations().put(otherFaction, rel);
		});
	}

	private String buildMage() {
		if (getActor().getMadness() < 5) {
			getActor().setArcane(0);
			// TODO: randomize beast name ?
			String log = String.format(Msg.getText(Msg.SUMMON_GHAST), getActor().getName(), getTarget().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			Actor ghast = ActorBuilder.buildGhast();
			sb.append(scenario.combat(ghast, getTarget()));
			if (getTarget().getHealth() > 0) {
				getTarget().addMadness(3);
			}
			if (ghast.getHealth() > 0) {
				// TODO: ghast is still alive!! could cause more havoc!
			}
		} else {
			getActor().setMadness(0);
			getActor().setArcane(0);
			int n = rnd.nextInt(2) + 1;
			String log = String.format(Msg.getText(Msg.SUMMON_HORRORS), getActor().getName(),
					getActor().getFaction().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getPoi().addRebellion(2);
			worseRelation(getActor().getFaction(), -10);
			List<Actor> horrors = ActorBuilder.buildHorrors(n, scenario);
			int i = 0;
			for (Actor horror : horrors) {
				Actor target = getPoi().getActors().get(i);
				sb.append(scenario.combat(horror, target) + "\n");
				if (target.getHealth() > 0) {
					target.addMadness(5);
				}
				i++;
			}
		}
		return sb.toString();
	}

	private String buildWarrior() {
		if (getActor().getMadness() < 5) {
			String log = String.format(Msg.getText(Msg.RAGE), getActor().getName(), getTarget().getName());
			sb.append(log);
			getActor().addQuestLog(log);
			sb.append(scenario.combat(getActor(), getTarget()));
			if (getTarget().getHealth() > 0) {
				getTarget().addMadness(3);
			}
		} else {
			// mad warrior start to killing random people and move to rebellion
			getActor().setMadness(0);
			String log = String.format(Msg.getText(Msg.BERSERK), getActor().getName(),
					getActor().getFaction().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getPoi().getActors().stream().forEach(a -> {
				if (!a.getName().equals(getActor().getName())) {
					a.addMadness(2);
				}
			});
			getPoi().addRebellion(2);
			worseRelation(getActor().getFaction(), -10);
		}
		return sb.toString();

	}

	private String buildThief() {
		if (getActor().getMadness() < 5) {
			getActor().addMoney(getTarget().getMoney());
			getTarget().setMoney(0);
			String log = String.format(Msg.getText(Msg.STEAL), getActor().getName(), getTarget().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			if (getTarget().getHealth() > 0) {
				getTarget().addMadness(3);
			}
		} else {
			getActor().setMadness(0);
			String log = String.format(Msg.getText(Msg.STEAL_ALL), getActor().getName(),
					getActor().getFaction().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getPoi().addRebellion(2);
			worseRelation(getActor().getFaction(), -10);
		}
		return sb.toString();
	}

	private String buildAssasin() {
		if (getActor().getMadness() < 5) {
			String log = String.format(Msg.getText(Msg.ASSASSIN), getActor().getName(), getTarget().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			scenario.removeActorFromPoi(getTarget().getName());
			if (getTarget().getHealth() > 0) {
				getTarget().addMadness(3);
			}
		} else {
			getActor().setMadness(0);
			String log = String.format(Msg.getText(Msg.ASSASSIN_KILLING_SLEEPING), getActor().getName(),
					getActor().getFaction().getName());
			getActor().addQuestLog(log);
			sb.append(log);
			getPoi().addRebellion(2);
			getPoi().addPopulation(-2000);
			worseRelation(getActor().getFaction(), -10);
		}
		return sb.toString();
	}

}
