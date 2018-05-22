package demo.player;

public class MyMain {

	public static void main(String[] args) {
		Messenger messenger = new Messenger();
		Player p1 = new Player("AAA", messenger);
		Player p2 = new Player("BBB", messenger);
		messenger.registerPlayer(p1);
		messenger.registerPlayer(p2);

		p1.sendMessage("BBB", "HelLo!");

	}


}
