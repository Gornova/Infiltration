package it.randomtower.infiltration.model.faction;

import java.awt.image.BufferedImage;
import java.util.Map;

public interface Faction {

	public String getName();

	public String getDescription();

	public FactionEnum getType();

	public BufferedImage getImage();

	public BufferedImage getImageRoll();

	public Map<String, Integer> getRelations();

	public void setRelations(Map<String, Integer> relations);

	public FactionStatusEnum getStatus();

	public void setStatus(FactionStatusEnum status);

	public String getTarget();

	public void setTarget(String target);

}
