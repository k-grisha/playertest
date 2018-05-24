package demo.player.messenger;

import demo.player.MessageDto;

/**
 * Messages manager
 */
public interface Messenger {
	/**
	 * Send message
	 *
	 * @param messageDto message
	 */
	void sendMessage(MessageDto messageDto);

	/**
	 * Get message by name of receiver
	 *
	 * @param name name of receiver
	 * @return message
	 */
	MessageDto getMessage(String name);
}
