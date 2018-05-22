package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.Player;
import demo.player.messenger.Messenger;

import java.util.HashMap;
import java.util.Map;

public class LocalMessenger implements Messenger {
	private Map<String, Player> players = new HashMap<String, Player>();

	@Override
	public void registerPlayer(Player player) {
		players.put(player.getName(), player);
	}

	@Override
	public void send(MessageDto messageDto) {
		Player receiver = players.get(messageDto.to);
		if (receiver != null) {
			Runnable task = () -> receiver.receiveMessage(messageDto);
			new Thread(task).start();
		}
	}
}
