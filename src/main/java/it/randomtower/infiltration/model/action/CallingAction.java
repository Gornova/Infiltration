package it.randomtower.infiltration.model.action;

import java.util.Optional;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.model.poi.BaseSecret;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class CallingAction extends BaseAction {

	private StringBuilder sb = new StringBuilder();

	public CallingAction() {
		setType(ActionEnum.CALLING);
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
			MapPanel.appendLog(getPoi().getName(),
					String.format(Msg.getText(Msg.CALLING_ACTION_COMPLETED), getActor().getName(), getPoi().getName()));
			Scenario scenario = Scenario.getInstance();
			// now secret is unlockable.. but only if specific png own the
			// district!
			BaseSecret secret = (BaseSecret) getPoi().getSecrets().get(0);
			if (scenario.isUnlocked(getPoi(), secret)) {
				String log = String.format(Msg.getText(Msg.HIDDEN_SECRET_FOUND_2));
				sb.append(log);
				MapPanel.displayEvent(sb.toString());
				getPoi().addClue(10);
				scenario.getAncient().addPower(1);
				scenario.removeSecret(getPoi(), secret);
				unconverHiddenPoi(scenario);
			} else {
				String log = String.format(Msg.getText(Msg.HIDDEN_SECRET_FOUND), getActor().getName(),
						getPoi().getName(), secret.getDistrict());
				sb.append(log);
				MapPanel.displayEvent(sb.toString());
				getPoi().addClue(5);
				scenario.unlockSecret(getPoi(), getActor(), secret);
				unconverHiddenPoi(scenario);
			}
		}
	}

	private void unconverHiddenPoi(Scenario scenario) {
		Optional<BasePoi> op = scenario.getPois().stream().filter(p -> p.isHidden()).findAny();
		if (op.isPresent()) {
			BasePoi p = op.get();
			p.setHidden(false);
			MapPanel.refreshPoi();
		}
	}

}