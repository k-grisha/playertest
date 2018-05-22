package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.Player;

public interface Messenger {
	void sendMessage(MessageDto messageDto);
	MessageDto getMessage(String name);
}
