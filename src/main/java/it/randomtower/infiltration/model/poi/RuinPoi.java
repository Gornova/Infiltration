package it.randomtower.infiltration.model.poi;

import java.awt.image.BufferedImage;
import java.util.List;

import it.randomtower.infiltration.citysimulation.District;
import it.randomtower.infiltration.citysimulation.DistrictBuilder;
import it.randomtower.infiltration.citysimulation.RuinSimulation;
import it.randomtower.infiltration.model.actor.Actor;

public class RuinPoi extends BasePoi {

	private RuinSimulation simulation;
	private BufferedImage image;
	private BufferedImage imageRoll;
	private BufferedImage imageRebellion;
	private List<String> connection;

	public RuinPoi(String name) {
		setName(name);
		this.simulation = new RuinSimulation(name);
	}

	public void addDistricts() {
		this.simulation.addDistricts(DistrictBuilder.buildRuin());
	}

	public void turn() {
		this.simulation.run();
	}

	@Override
	protected void addActorCallBack(Actor actor) {
		this.simulation.addPlayer(actor);
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

	public void setConnection(List<String> connection) {
		this.connection = connection;
	}

	public List<String> getConnection() {
		return connection;
	}

	@Override
	public List<District> getDistricts() {
		return simulation.getDistricts();
	}

	@Override
	public void removeActor(Actor png) {
		this.getActors().remove(png);
		this.simulation.removePlayer(png);
	}

}
