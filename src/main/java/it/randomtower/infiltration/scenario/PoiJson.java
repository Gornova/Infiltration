package it.randomtower.infiltration.scenario;

import java.util.ArrayList;
import java.util.List;

public class PoiJson {

	private String name;
	private String description;
	private String nickname;
	private int x;
	private int y;
	private String status;
	private int food;
	private int population;
	private int work;
	private int money;
	private List<String> connections = new ArrayList<>();
	private String type;
	private String image;
	private String imageRoll;
	private String imageRebellion;
	private Boolean secret;
	private Boolean hidden;

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public int getPopulation() {
		return population;
	}

	public int getWork() {
		return work;
	}

	public void setWork(int work) {
		this.work = work;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public List<String> getConnections() {
		return connections;
	}

	public void setConnections(List<String> connections) {
		this.connections = connections;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public String getImageRoll() {
		return imageRoll;
	}

	public String getImageRebellion() {
		return imageRebellion;
	}

	public Boolean getSecret() {
		return secret;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setImageRoll(String imageRoll) {
		this.imageRoll = imageRoll;
	}

	public void setImageRebellion(String imageRebellion) {
		this.imageRebellion = imageRebellion;
	}

	public void setSecret(Boolean secret) {
		this.secret = secret;
	}

}
