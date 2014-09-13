package tasks;

import org.tbot.methods.Bank;
import org.tbot.methods.Camera;
import org.tbot.methods.GameObjects;
import org.tbot.methods.Time;
//import org.tbot.util.Filter;
import org.tbot.wrappers.GameObject;
//import org.tbot.wrappers.Item;

public class BankClass {
	GameObject booth = GameObjects.getNearest("Bank booth");
	
	public BankClass() {
		
//		final Filter<Item> itemsToBank = new Filter<Item>() {
//			@Override
//			public boolean accept(final Item InvItem) {
//				return 
//			}
//		};
		
		if (booth != null && booth.isOnScreen()) {
			if (Bank.isOpen()) {
				Bank.depositAllExcept(307, 313);
				Time.sleep(400, 800);
			} else {
				Bank.open();
				Time.sleep(600, 1200);
			}
		} else {
			Camera.tiltRandomly();
			Camera.turnTo(booth);
		}
	}
}
