package it.randomtower.infiltration.model.action;

import java.util.Random;

import it.randomtower.infiltration.Msg;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.ui.map.MapPanel;

public class InvestigateAction extends BaseAction {

	private Scenario scenario;
	private Random rnd = new Random();

	public InvestigateAction(Scenario scenario) {
		this.scenario = scenario;
		setType(ActionEnum.INVESTIGATE);
	}

	// TODO: keep counter, is finished logic into act and move real not-abstract
	// and action specific code into a callback, to avoid duplicated code
	@Override
	public void act() {
		if (isFinished()) {
			return;
		}
		counter++;
		getActor().setActionCounter(getTime() - counter);
		if (counter >= turns) {
			setFinished(true);
			float test = rnd.nextFloat();
			if (test > 0.6f) {
				MapPanel.displayEvent(getActor().getName() + " " + Msg.getText(Msg.AWARENESS_RISE));
				scenario.addAwarness(1);
			} else {
				MapPanel.displayEvent(getActor().getName() + " " + Msg.getText(Msg.AWARENESS_RISE_DOWN));
			}
		}
	}

}
