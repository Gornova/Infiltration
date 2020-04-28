package it.randomtower.infiltration.model.action;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.ui.map.MapPanel;

public class CorruptAction extends BaseAction {

	public CorruptAction() {
		setType(ActionEnum.CORRUPT);
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
			getActor().setCorrupted(true);
			MapPanel.appendLog(getPoi().getName(),
					String.format(Msg.getText(Msg.ACTION_CORRUPT_COMPLETED), getActor().getName(), getPoi().getName()));
			getPoi().addClue(1);
		}
	}

}
