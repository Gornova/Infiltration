package it.randomtower.infiltration.model.faction;

import java.awt.image.BufferedImage;
import java.util.Map;

public class BaseFaction implements Faction {

	private String name;

	private String description;

	private FactionEnum type;

	private BufferedImage image;

	private BufferedImage imageRoll;

	private Map<String, Integer> relations;

	private FactionStatusEnum status;

	private String target;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FactionEnum getType() {
		return type;
	}

	public void setType(FactionEnum type) {
		this.type = type;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public Map<String, Integer> getRelations() {
		return relations;
	}

	public void setRelations(Map<String, Integer> relations) {
		this.relations = relations;
	}

	public FactionStatusEnum getStatus() {
		return status;
	}

	public void setStatus(FactionStatusEnum status) {
		this.status = status;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public BufferedImage getImageRoll() {
		return imageRoll;
	}

	public void setImageRoll(BufferedImage imageRoll) {
		this.imageRoll = imageRoll;
	}

}
