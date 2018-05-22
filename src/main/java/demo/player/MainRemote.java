package demo.player;

import demo.player.messenger.Messenger;
import demo.player.messenger.RemoteMessenger;

public class MainRemote {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("New Player name missed");
			return;
		}

		Messenger messenger = new RemoteMessenger();
		Player player = new Player(args[0], messenger);

		player.register();
		if (args.length > 1) {
			player.sendMessage(args[1], "HelLo!");
		}

		System.out.println("finish");
	}


}
