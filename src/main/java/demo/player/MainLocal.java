package demo.player;

import demo.player.messenger.LocalMessenger;
import demo.player.messenger.Messenger;

public class MainLocal {

	/**
	 * Start Players in the same Java process
	 */
	public static void main(String[] args) {
		Messenger messenger = new LocalMessenger();
		new Player("Receiver", messenger);
		new Player("Initiator", messenger).sendMessage("Receiver", "Hello!");
	}

}
