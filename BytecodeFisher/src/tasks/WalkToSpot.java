package tasks;

import org.tbot.methods.Players;
import org.tbot.methods.Time;
import org.tbot.methods.walking.Walking;
import org.tbot.wrappers.Tile;

public class WalkToSpot {
	private Tile spotTile = new Tile (3088, 3230);

	public WalkToSpot() {
		
		if (!Players.getLocal().isMoving()) {
			Walking.walkTileMM(spotTile);
			Time.sleep(600, 1200);
		}
	}
}
