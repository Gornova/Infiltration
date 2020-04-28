package it.randomtower.infiltration.ui.map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import de.anormalmedia.vividswinganimations.listener.AnimationListener;
import it.randomtower.infiltration.Main;
import it.randomtower.infiltration.animation.PngIcon;
import it.randomtower.infiltration.model.actor.Actor;
import it.randomtower.infiltration.model.challenge.Challenge;
import it.randomtower.infiltration.model.poi.BasePoi;
import it.randomtower.infiltration.model.poi.CityPoi;
import it.randomtower.infiltration.model.poi.Poi;
import it.randomtower.infiltration.model.poi.RuinPoi;
import it.randomtower.infiltration.scenario.Scenario;
import it.randomtower.infiltration.scenario.ScenarioBuilder;
import it.randomtower.infiltration.timer.GameTimer;
import it.randomtower.infiltration.ui.AwarnessLevelPanel;
import it.randomtower.infiltration.ui.EventsListPanel;
import it.randomtower.infiltration.ui.HelpButton;
import it.randomtower.infiltration.ui.PowerLevelPanel;
import it.randomtower.infiltration.ui.TurnButton;
import it.randomtower.infiltration.ui.TurnLabel;
import it.randomtower.infiltration.ui.action.ChallengePanel;
import it.randomtower.infiltration.ui.action.FightPanel;
import it.randomtower.infiltration.ui.action.MoveActionPanel;
import it.randomtower.infiltration.ui.actor.ActorPanel;
import it.randomtower.infiltration.ui.actor.QuestButton;
import it.randomtower.infiltration.ui.event.EventPanel;
import it.randomtower.infiltration.ui.log.ActorQuestLog;
import it.randomtower.infiltration.ui.log.LogPanel;
import it.randomtower.infiltration.ui.poi.PoiPanel;

@SuppressWarnings("serial")
public class MapPanel extends JPanel implements ActionListener {

	private BufferedImage baseMap;
	private Scenario scenario;
	private PoiPanel poiPanel;
	private ActorPanel actorPanel;
	private static TurnButton turnButton;
	private static Main mainForm;
	private TurnLabel turnLabel;
	private static GameTimer gameTimer;
	private PowerLevelPanel powerLevelPanel;
	private AwarnessLevelPanel awarnessLevelPanel;
	private HelpButton helpButton;
	private ChallengePanel challengePanel;
	private FightPanel fightPanel;
	private MoveActionPanel moveActionPanel;
	private static ActorQuestLog actorQuestLog;
	private static EventsListPanel eventListPanel;
	private static EventPanel eventPanel;
	private static LogPanel logPanel;

	private static MapPanel instance;

	/**
	 * Create the panel.
	 * 
	 * @param main
	 * 
	 * @throws IOException
	 */
	public MapPanel(BufferedImage image, Main main) throws IOException {
		setSize(new Dimension(1024, 768));
		setLayout(null);
		this.instance = this;
		this.baseMap = image;
		this.mainForm = main;

		scenario = ScenarioBuilder.buildDemo();
		scenario.setMapPanel(this);
		poiPanel = new PoiPanel();
		poiPanel.setBounds(1024 - 256, 0, 256, 650);
		poiPanel.setVisible(false);

		actorPanel = new ActorPanel(scenario, this, poiPanel);
		actorPanel.setBounds(1024 - 512, 0, 256, 650);
		actorPanel.setVisible(false);

		poiPanel.setActorPanel(actorPanel);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				poiPanel.setVisible(false);
				actorPanel.setVisible(false);
			}
		});

		gameTimer = new GameTimer(1000, this);

		turnLabel = new TurnLabel("Turn: 0", 10, 5);

		turnButton = new TurnButton(Main.getFileResource("if_Play_984752.png"),
				Main.getFileResource("if_Play_984752_roll.png"), Main.getFileResource("if_pause_1167976.png"),
				Main.getFileResource("if_pause_1167976_roll.png"), 12, 45, gameTimer);

		powerLevelPanel = new PowerLevelPanel(5, 90);
		awarnessLevelPanel = new AwarnessLevelPanel(5, 280);

		logPanel = new LogPanel();
		logPanel.setBounds(88, 650, 788, 80);

		actorQuestLog = new ActorQuestLog();
		actorQuestLog.setBounds(256, 128, 512, 400);
		actorQuestLog.setVisible(false);

		eventPanel = new EventPanel(gameTimer);
		eventPanel.setBounds(256, 128, 512, 400);
		eventPanel.setVisible(false);

		eventListPanel = new EventsListPanel(eventPanel);
		eventListPanel.setBounds(5, 500, 40, 500);

		helpButton = new HelpButton(Main.getFileResource("if_help_172477.png"),
				Main.getFileResource("if_help_172477_roll.png"), 300, 10, eventPanel);

		challengePanel = new ChallengePanel(this);
		challengePanel.setBounds(256, 128, 512, 400);
		challengePanel.setVisible(false);
		add(challengePanel);

		fightPanel = new FightPanel(this);
		fightPanel.setBounds(256, 128, 512, 400);
		fightPanel.setVisible(false);
		add(fightPanel);

		moveActionPanel = new MoveActionPanel(this);
		moveActionPanel.setBounds(256, 128, 256, 400);
		moveActionPanel.setVisible(false);
		add(moveActionPanel);

		initScenario();
	}

	private void initScenario() throws IOException {
		// convert scenario data to map elements
		removeAll();
		for (BasePoi poi : scenario.getPois()) {
			switch (poi.getType()) {
			case CITY:
				CityPoi p = (CityPoi) poi;
				CityMapPanel panel = new CityMapPanel(p, (int) p.getX(), (int) p.getY());
				CityButton c = new CityButton(p, p.getImage(), p.getImageRoll(), p.getImageRebellion(), poiPanel);
				panel.add(c, 0);

				AgentMapButton amb = new AgentMapButton(p, Main.getFileResource("PointofInterest.png"),
						Main.getFileResource("PointofInterest_blue.png"));
				panel.add(amb, 0);

				add(panel, 0);
				break;
			case RUIN:
				RuinPoi r = (RuinPoi) poi;
				CityMapPanel rmp = new CityMapPanel(r, (int) r.getX(), (int) r.getY());
				CityButton rb = new CityButton(r, r.getImage(), r.getImageRoll(), r.getImageRebellion(), poiPanel);
				rmp.add(rb, 0);

				AgentMapButton rmb = new AgentMapButton(r, Main.getFileResource("PointofInterest.png"),
						Main.getFileResource("PointofInterest_blue.png"));
				rmp.add(rmb, 0);
				rmp.setVisible(!r.isHidden());
				add(rmp, 0);
				break;
			default:
				break;
			}
		}

		// add hidden panels
		add(poiPanel, 0);
		add(actorPanel, 0);
		add(turnButton, 0);
		add(turnLabel, 0);
		add(logPanel, 0);
		add(actorQuestLog, 0);
		add(eventPanel, 2);
		add(eventListPanel, 2);
		add(powerLevelPanel, 0);
		add(awarnessLevelPanel, 0);
		add(helpButton, 0);
		add(challengePanel, 2);
		add(fightPanel, 2);
		add(moveActionPanel, 2);

		powerLevelPanel.setPower(scenario.getAncient().getPower());
		awarnessLevelPanel.setAwarness(scenario.getAwarness());

		logPanel.appendLog("", "An ancient god awakes..\nSelect city label to see specific city logs here");

		helpButton.displayHelp();

	}

	public static void appendLog(String city, String log) {
		logPanel.appendLog(city, log);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(baseMap, 0, 0, null);
		drawConnections(g);
	}

	private void drawConnections(Graphics g) {
		StringPairCache cache = new StringPairCache();
		for (BasePoi p : scenario.getPois()) {
			if (!p.isHidden()) {
				float sx = p.getX();
				float sy = p.getY();
				for (String connection : p.getConnection()) {
					BasePoi target = scenario.findPoi(connection);
					if (!target.isHidden()) {
						float tx = target.getX();
						float ty = target.getY();
						StringPair pair = new StringPair(p.getName(), connection);
						if (!cache.contains(pair)) {
							cache.put(pair);
							drawLines(g, (int) sx + 32, (int) sy + 32, (int) tx + 32, (int) ty + 32);
						}
					}
				}
			}
		}
	}

	void drawLines(Graphics g, int sx, int sy, int tx, int ty) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.BLACK);

		float[] dashingPattern1 = { 4f, 2f };
		Stroke stroke1 = new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1.0f, dashingPattern1, 2.0f);

		Stroke tmp = g2d.getStroke();
		g2d.setStroke(stroke1);
		g2d.drawLine(sx, sy, tx, ty);

		g2d.setColor(Color.BLACK);
		g2d.setStroke(tmp);
	}

	public Scenario getScenario() {
		return scenario;
	}

	public void refresh() {
		for (int i = 0; i < getComponents().length; i++) {
			Component c = getComponents()[i];
			if (c instanceof CityMapPanel) {
				((CityMapPanel) c).refresh();
				for (int j = 0; j < ((CityMapPanel) c).getComponents().length; j++) {
					Component d = ((CityMapPanel) c).getComponents()[j];
					if (d instanceof CityButton) {
						((CityButton) d).refresh();
					}
					if (d instanceof AgentMapButton) {
						((AgentMapButton) d).refresh();
					}
					if (d instanceof QuestButton) {
						// ((QuestButton) d).refresh();
					}
				}
			}
		}

		poiPanel.refresh();
		try {
			actorPanel.refresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainForm.refresh();
		turnLabel.refresh(scenario.getTurnCounter());
		eventListPanel.refresh();

		powerLevelPanel.setPower(scenario.getAncient().getPower());
		awarnessLevelPanel.setAwarness(scenario.getAwarness());
		challengePanel.refresh();
		moveActionPanel.refresh();
	}

	public static void displayEvent(String text) {
		eventListPanel.addEvent(text);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		getScenario().turn();
		refresh();
		eventListPanel.turn();
	}

	public static void displayActorLogPanel(boolean value, Actor actor) {
		actorQuestLog.displayLog(actor);
		actorQuestLog.setVisible(value);
	}

	public void displayChallengePanel(Challenge challenge) {
		this.challengePanel.setChallenge(challenge);
		this.challengePanel.setVisible(true);
		refresh();
	}

	public void hideChallengePanel() {
		this.challengePanel.setVisible(false);
		refresh();
	}

	public void addPngIcon(Actor png, BasePoi from, BasePoi target) throws IOException {
		png.setX(from.getX());
		png.setY(from.getY());
		PngIcon pngIcon = new PngIcon(png.getFaction().getImage(), png.getFaction().getImage(), png.getName(),
				(int) from.getX(), (int) from.getY(), 128, 128, png);
		pngIcon.setLocation((int) from.getX(), (int) from.getY());
		add(pngIcon, -1);
		refresh();
		// animate
		AnimationListener animationListener = new AnimationListener() {

			@Override
			public void animationUpdated() {
				// System.out.println("" + element.getLocationX() + "," +
				// element.getLocationY());
			}

			@Override
			public void animationStarted() {
				// System.out.println("started");
			}

			@Override
			public void animationFinished() {
				// System.out.println("finished");
				pngIcon.setVisible(false);
				remove(pngIcon);
			}
		};
		pngIcon.animate((int) target.getX(), (int) target.getY(), animationListener);
	}

	public static void refreshPoi() {
		instance.refresh();
	}

	public void displayMoveActionPanel(Actor target, Poi poi) {
		moveActionPanel.setActorAndPoi(target, (BasePoi) poi);
		moveActionPanel.setVisible(true);

	}

	public void displayFightPanel(Actor attacker, Actor defender) {
		this.fightPanel.setupCombat(attacker, defender);
		this.fightPanel.setVisible(true);
		refresh();
	}

}
