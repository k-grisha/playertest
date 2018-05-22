package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.Player;

public interface Messenger {
	void registerPlayer(Player player);

	void send(MessageDto messageDto);
}
