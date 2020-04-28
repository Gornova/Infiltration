package it.randomtower.infiltration.model.ancient;

import it.randomtower.infiltration.model.action.ActionEnum;

public class Ancient {

	private int power;

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getCost(ActionEnum actionName) {
		// TODO: uncomment for release
		switch (actionName) {
		case CORRUPT:
		case MANIPULATE:
		case CALLING:
		case MOVING:
			return 11 - power;
		default:
			break;
		}
		return 1;
	}

	public void pay() {
		// pay for one action
		if (power - 1 > 0) {
			power -= 1;
		} else {
			power = 0;
		}

	}

	public void addPower(long c) {
		power += c;
	}

}
