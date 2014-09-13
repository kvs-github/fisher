package tasks;

import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.wrappers.NPC;

public class FishShrimps {
	NPC spot = Npcs.getNearest("Fishing spot");
	
	public FishShrimps() {
		
		if (Players.getLocal().getAnimation() == -1) {
			spot.interact("Net");
			Time.sleep(800, 1600);
		}
	}
}	
