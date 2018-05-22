package demo.player;

import java.util.HashMap;
import java.util.Map;

public class Messenger {
	private Map<String, Player> players = new HashMap<String, Player>();

	public void registerPlayer(Player player) {
		players.put(player.getName(), player);
	}

	public void send(MessageDto messageDto) {
		Runnable task = () -> players.get(messageDto.to).receiveMessage(messageDto);
		new Thread(task).start();
	}
}
