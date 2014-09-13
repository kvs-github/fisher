package tasks;

import org.tbot.methods.Npcs;
import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.wrappers.NPC;

public class FishSardine {
	
	NPC spot = Npcs.getNearest("Fishing spot");
	
	public FishSardine() {
		
		if (Players.getLocal().getAnimation() == -1) {
			spot.interact("Bait");
			Time.sleep(600, 1200);
		}
		
	}

}
