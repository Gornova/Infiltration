package it.randomtower.infiltration.model.action;

/**
 * Possible actions type for an actor
 */
public enum ActionEnum {

	IDLE, QUEST, CORRUPT, MANIPULATE, CALLING, INVESTIGATE, MOVE_INVESTIGATE, MOVING;

	public boolean isPlayerAction() {
		switch (this) {
		case MANIPULATE:
		case CORRUPT:
		case CALLING:
			return true;
		case IDLE:
		case QUEST:
		case INVESTIGATE:
		case MOVE_INVESTIGATE:
			return false;
		default:
			return false;
		}
	}

}
