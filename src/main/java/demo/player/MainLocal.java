package demo.player;

import demo.player.messenger.LocalMessenger;
import demo.player.messenger.Messenger;

public class MainLocal {

	public static void main(String[] args) {
		Messenger messenger = new LocalMessenger();
		Player p1 = new Player("AAA", messenger);
		Player p2 = new Player("BBB", messenger);
		p1.register();
		p2.register();
		p1.sendMessage("BBB", "HelLo!");
	}


}
