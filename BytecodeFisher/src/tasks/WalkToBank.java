package tasks;

import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Tile;

public class WalkToBank {
	Tile bankTile = new Tile(3092, 3243);
	
	public WalkToBank() {
		
		if (!Players.getLocal().isMoving()) {
			Walking.walkTileMM(bankTile);
			Time.sleep(600, 1200);
		}
	}
}
