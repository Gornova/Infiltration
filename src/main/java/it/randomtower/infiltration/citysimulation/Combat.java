package it.randomtower.infiltration.citysimulation;

import java.util.Random;

import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.scenario.Scenario;

public class Combat {

	private static Random rnd = new Random();

	public static void combat(Simulation game, Actor attaccante, District struttura) {
		int r1 = rnd.nextInt(11);
		int r2 = rnd.nextInt(11);
		// System.out.println(String.format("%s assalta %s che appartiene al
		// giocatore %s", attaccante.getName(),
		// struttura.nome, struttura.padrone));
		Actor difensore = game.find(struttura.padrone);
		int t1 = attaccante.getFollowers() + r1;
		// System.out.println(String.format("%s attacca con base %d seguaci + %d
		// dado = %d ", attaccante.getName(),
		// attaccante.getFollowers(), r1, t1));
		int t2 = difensore.getFollowers() + struttura.difesa + r2;
		// System.out.println(String.format("%s difende con base %d seguaci + %d
		// dado + %d difesa struttura = %d ",
		// difensore.getName(), difensore.getFollowers(), r2, struttura.difesa,
		// t2));
		if (t1 > t2) {
			// System.out.println(String.format(
			// "Attaccante %s vince! La struttura %s cambia padrone. Attaccante
			// perde %d seguaci negli scontri ",
			// attaccante.getName(), struttura.nome, t1 - t2));
			struttura.padrone = attaccante.getName();
			int d = t1 - t2;
			attaccante.setFollowers(attaccante.getFollowers() - d < 0 ? 0 : attaccante.getFollowers() - d);
		} else {
			// System.out.println(String.format("Difensore %s vince! Difensore
			// perde %d seguaci negli scontri",
			// difensore.getName(), t2 - t1));
			int d = t2 - t1;
			difensore.setFollowers(difensore.getFollowers() - d < 0 ? 0 : difensore.getFollowers() - d);
		}
	}

	public static String combat(Scenario scenario, Actor attaccante, Actor difensore) {
		int r1 = rnd.nextInt(11);
		int r2 = rnd.nextInt(11);
		int t1 = attaccante.getFollowers() + r1 + attaccante.getAttack();
		System.out.println("attacker total " + t1);
		int t2 = difensore.getFollowers() + r2 + difensore.getAttack();
		System.out.println("defender total " + t2);
		if (t1 > t2) {
			System.out.println("attack wins!");
			difensore.setDead(true);
			scenario.removeActorFromPoi(difensore.getName());
			return attaccante.getName() + " wins!";
		} else {
			System.out.println("defend wins!");
			attaccante.setDead(true);
			scenario.removeActorFromPoi(attaccante.getName());
			return difensore.getName() + " wins!";
		}
	}

}
