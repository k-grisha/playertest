package demo.player.messenger;

import demo.player.MessageDto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Messages manager implementation for players in the same process
 */
public class LocalMessenger implements Messenger {
	// Store messages in queues for each user
	private Map<String, LinkedBlockingQueue<MessageDto>> playersMessages = new HashMap<>();

	@Override
	public void sendMessage(MessageDto messageDto) {
		LinkedBlockingQueue<MessageDto> messages = playersMessages.computeIfAbsent(messageDto.to, k -> new LinkedBlockingQueue<>());
		try {
			messages.put(messageDto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MessageDto getMessage(String name) {
		LinkedBlockingQueue<MessageDto> messages = playersMessages.get(name);
		return messages != null ? messages.poll() : null;
	}
}
