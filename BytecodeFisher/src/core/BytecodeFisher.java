package core;

import java.awt.Color;
import java.awt.Graphics;

import gui.GUIClass;

import org.tbot.internal.AbstractScript;
import org.tbot.internal.Manifest;
import org.tbot.internal.event.listeners.PaintListener;
import org.tbot.methods.Players;
import org.tbot.methods.Skills;
import org.tbot.methods.Skills.Skill;
import org.tbot.methods.tabs.Inventory;
import org.tbot.wrappers.Area;
import org.tbot.wrappers.Player;
import org.tbot.wrappers.Tile;

import tasks.BankClass;
import tasks.FishSardine;
import tasks.FishShrimps;
import tasks.WalkToBank;
import tasks.WalkToSpot;
import data.State;

@Manifest(name = "BC Fisher", authors = "Bytecode", version = 1.0, description = "Draynor fisher and banker")
public class BytecodeFisher extends AbstractScript implements PaintListener {
	
	private Area bankArea = new Area(3092, 3240, 3095, 3246);
//	private Area fishArea = new Area (3087, 3223, 3091, 3233);
	private Tile spotTile = new Tile (3088, 3230);
	private static long time = System.currentTimeMillis();
	private static Skill skill = Skill.Fishing;
	private static int startLevel = Skills.getRealLevel(skill);

	public boolean onStart() {
		new GUIClass();
		log("Please report any bugs you encounter on the thread.");
		return true;
	}

	@Override
	public int loop() {
		
		if (!GUIClass.start) return 2000;
			
		final State state = getState();
		
		switch (state) {
		
		case FISH_SHRIMPS:
			new FishShrimps();
			break;
		case FISH_SARDINE:
			new FishSardine();
			break;
		case BANK:
			new BankClass();
			break;
		case WALK_TO_SPOT:
			new WalkToSpot();
			break;
		case WALK_TO_BANK:
			new WalkToBank();
			break;
		default:
			log("Something went wrong, please submit a bug report in the thread.");
			break;
		}
		
		return 100;
	}
	
	public State getState() {
		if (timeToFishShrimps()) {
			return State.FISH_SHRIMPS;
		}
		else if (timeToFishSardine()){
			return State.FISH_SARDINE;
		}
		else if (timeToBank()) {
			return State.BANK;
		}
		else if (walkToSpotNeeded()) {
			return State.WALK_TO_SPOT;
		}
		else if (walkToBankNeeded()) {
			return State.WALK_TO_BANK;
		}
		else {
			log("Invalid state");
			return null;
		}
	}
	
	
	
	boolean timeToFishShrimps() {
		String text_field = GUIClass.getOptionTF();
		
		if (!text_field.equalsIgnoreCase("shrimp"))
			return false;
		
		Player p = Players.getLocal();

		if (p == null)
			return false;

		if (Inventory.contains("Shrimp") || Inventory.contains("Sardine"))
			return false;

		if (spotTile.distance(p.getLocation()) >= 5)
			return false;
		
		boolean has_net = Inventory.contains("Small fishing net");
		
		if (!has_net)
			return false;
		
		return true;
	}
	
	private boolean timeToFishSardine() {
		
		String text_field = GUIClass.getOptionTF();
		
		return (!Inventory.contains("Shrimp")
				&& !Inventory.contains("Sardine")) 
				&& spotTile.distance(Players.getLocal().getLocation()) < 5
				&& text_field.equalsIgnoreCase("Sardine")
				&& Inventory.contains("Fishing rod")
				&& !Inventory.isFull();
	}
	
	private boolean timeToBank() {
		return Inventory.isFull() && bankArea.contains(Players.getLocal());
	}
	
	private boolean walkToSpotNeeded() {
		return !Inventory.contains("Sardine") && !(spotTile.distance(Players.getLocal().getLocation())  < 4);
	}
	
	private boolean walkToBankNeeded() {
		return Inventory.isFull() && !bankArea.contains(Players.getLocal());
	}
	
	private static String format(long time) {
		final StringBuilder t = new StringBuilder();
		final long total_secs = time / 1000;
		final long total_mins = total_secs / 60;
		final long total_hrs = total_mins / 60;
		final int secs = (int) total_secs % 60;
		final int mins = (int) total_mins % 60;
		final int hrs = (int) total_hrs % 60;
		
		if (hrs < 10)
			t.append("0");
		t.append(hrs);
		t.append(":");
		
		if (mins < 10)
			t.append("0");
		t.append(mins);
		t.append(":");
		
		if (secs < 10) 
			t.append("0");
		t.append(secs);
		
		return t.toString();
		
	}
	
	
	public static long getRunTime() {
		return System.currentTimeMillis() - time;
	}
	
	public static String getFormattedRuntime() {
		return format(getRunTime());
	}
	
	public static int getCurrentLevel() {
		return Skills.getRealLevel(skill);
	}
	
	public static int getLevelsGained() {
		return getCurrentLevel() - startLevel;
	}
	
	@Override
	public void onRepaint(Graphics g) {
		g.setColor(Color.GREEN);
		g.drawString("Time running: " + getFormattedRuntime(), 7, 55);
		g.drawString("Levels gained: " + getLevelsGained() , 7, 70);
		
	}
	

}
