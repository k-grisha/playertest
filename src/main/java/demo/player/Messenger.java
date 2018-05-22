package demo.player;

import java.util.HashMap;
import java.util.Map;

public class Messenger {
	private Map<String, Player> players = new HashMap<String, Player>();

	public void registerPlayer(Player player) {
		players.put(player.getName(), player);
	}

	public void send(MessageDto messageDto) {
		Player receiver = players.get(messageDto.to);
		if (receiver != null) {
			Runnable task = () -> receiver.receiveMessage(messageDto);
			new Thread(task).start();
		}
	}
}
