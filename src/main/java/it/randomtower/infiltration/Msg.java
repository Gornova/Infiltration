package it.randomtower.infiltration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Msg {

	public static final String TITLE = "title";
	public static final String ACTION_ACCESS_STRUCTURE = "action_access_structure";
	public static final String ACTION_SOLVE_QUEST = "action_solve_quest";
	public static final String ACTION_SOLVE_QUEST_NO_LUCK = "action_solve_quest_no_luck";
	public static final String ACTION_SOLVE_QUEST_NO_LUCK_LOSE_TAG = "action_solve_quest_no_luck_lose_tag";
	public static final String ACTION_ATTACK_STRUCTURE = "action_attack_structure";
	public static final String ACTION_IMPROVE_MONEY = "action_improve_money";
	public static final String ACTION_IMPROVE_FOLLOWERS = "action_improve_followers";
	public static final String ACTION_RECRUIT_FOLLOWERS = "action_recruit_followers";
	public static final String CALLING_ACTION_COMPLETED = "action_calling_completed";
	public static final String HIDDEN_SECRET_FOUND = "hidden_secret_found";
	public static final String HIDDEN_SECRET_FOUND_2 = "hidden_secret_found_2";
	public static final String ACTION_CORRUPT_COMPLETED = "action_corrupt_completed";
	public static final String AWARENESS_RISE = "awareness_rise";
	public static final String AWARENESS_RISE_DOWN = "awareness_rise_down";
	public static final String ACTION_MANIPULATE_COMPLETED = "action_manipulate_completed";
	public static final String CORRUPTION_COMPLETED = "corruption_completed";
	public static final String HIGH_TAXES = "high_taxes";
	public static final String SUMMON_GHAST = "summon_ghast";
	public static final String SUMMON_HORRORS = "summon_horrors";
	public static final String RAGE = "rage";
	public static final String BERSERK = "berserk";
	public static final String STEAL = "steal";
	public static final String STEAL_ALL = "steal_all";
	public static final String ASSASSIN = "assassin";
	public static final String WIN_MSG = "win_msg";
	public static final String LOSE_MSG = "lose_msg";
	public static final String REBELLION_WINS = "rebellion_wins";
	public static final String REBELLION_LOSE = "rebellion_lose";
	public static final String REBELLION_CONTINUE = "rebellion_continues";
	public static final String CLUE_FOUND = "clue_found";
	public static final String WAR_END = "war_end";
	public static final String WAR_START = "war_start";
	public static final String FAMINE_START = "famine_start";
	public static final String REBELLION_START = "rebellion_start";
	public static final String WELCOME_MSG = "welcome_msg";
	public static final String PNG_MOVE_FROM_TO_CITY = "png_move_from_to_city";
	public static final String ASSASSIN_KILLING_SLEEPING = "assassin_killing_sleeping";
	private static Properties properties;

	public static String getText(final String key) {
		if (properties == null) {
			InputStream input;
			try {
				ClassLoader classLoader = Main.class.getClassLoader();
				input = classLoader.getResourceAsStream("messages.properties");

				properties = new Properties();
				properties.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties.getProperty(key);
	}

}
