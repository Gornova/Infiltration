package it.randomtower.infiltration.model.actor.tag;

import it.randomtower.infiltration.ChallengeType;

public class Tag {

	private String name;
	private String description;
	private float value;
	private TagEffect effect;
	private boolean bonus;
	private ChallengeType challengeType;

	public Tag() {
	}

	public Tag(String name, String description, TagEffect effect, boolean bonus, ChallengeType challengeType,
			float value) {
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.bonus = bonus;
		this.challengeType = challengeType;
		this.value = value;
	}

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

	public TagEffect getEffect() {
		return effect;
	}

	public void setEffect(TagEffect effect) {
		this.effect = effect;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Tag) {
			Tag otherTag = (Tag) obj;
			return name.equals(otherTag.name);
		}
		return false;
	}

	public boolean isBonus() {
		return bonus;
	}

	public void setBonus(boolean bonus) {
		this.bonus = bonus;
	}

	public ChallengeType getChallengeType() {
		return challengeType;
	}

	public void setChallengeType(ChallengeType challengeType) {
		this.challengeType = challengeType;
	}

	@Override
	public String toString() {
		return "name=" + name + ", effect=" + effect.toString() + ", value=" + value;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}
