package it.randomtower.infiltration.model.poi;

import java.awt.image.BufferedImage;
import java.util.List;

import it.randomtower.infiltration.citysimulation.CitySimulation;
import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.citysimulation.DistrictBuilder;
import it.randomtower.infiltration.citysimulation.QuestBuilder;
import it.randomtower.infiltration.model.actor.Actor;

public class CityPoi extends BasePoi {

	private CitySimulation simulation;
	private BufferedImage image;
	private BufferedImage imageRoll;
	private BufferedImage imageRebellion;

	public CityPoi(String name) {
		setName(name);
		this.simulation = new CitySimulation(name);
		this.simulation.addQuests(QuestBuilder.buildQuests());
	}

	public void addDistricts() {
		this.simulation.addDistricts(DistrictBuilder.build());
	}

	@Override
	protected void addActorCallBack(Actor actor) {
		this.simulation.addPlayer(actor);
	}

	public void turn() {
		this.simulation.run();
	}

	@Override
	public List<District> getDistricts() {
		return simulation.getDistricts();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage getImageRoll() {
		return imageRoll;
	}

	public void setImageRoll(BufferedImage imageRoll) {
		this.imageRoll = imageRoll;
	}

	public BufferedImage getImageRebellion() {
		return imageRebellion;
	}

	public void setImageRebellion(BufferedImage imageRebellion) {
		this.imageRebellion = imageRebellion;
	}

	public CitySimulation getSimulation() {
		return simulation;
	}

	@Override
	public void removeActor(Actor png) {
		this.getActors().remove(png);
		this.simulation.removePlayer(png);
	}
}
