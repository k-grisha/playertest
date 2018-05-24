package demo.player;

import demo.player.messenger.Messenger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Player {
	private final static int MAX_MESSAGES = 1;
	private final String name;
	private final Messenger messenger;
	private int sentCounter;
	private int receiveCounter;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Player(String name, Messenger messenger) {
		this.name = name;
		this.messenger = messenger;
		scheduler.scheduleAtFixedRate(this::getMessage, 1000, 200, TimeUnit.MILLISECONDS);
	}

	private void getMessage() {
		MessageDto messageDto = messenger.getMessage(name);
		if (messageDto == null) {
			return;
		}
		System.out.println(String.format("%s receive message from %s. Text: '%s'", name, messageDto.from, messageDto.text));
		sendMessage(messageDto.from, messageDto.text.substring(0, messageDto.text.length() - 2));
		receiveCounter++;
		if (receiveCounter >= MAX_MESSAGES) {
			scheduler.shutdownNow();
		}
	}

	public void sendMessage(String to, String text) {
		if (sentCounter >= MAX_MESSAGES) {
			return;
		}
		System.out.println(name + " send message #" + sentCounter);
		messenger.sendMessage(new MessageDto(name, to, text + String.format("%02d", sentCounter)));
		sentCounter++;
	}


	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				'}';
	}
}