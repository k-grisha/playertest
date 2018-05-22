package demo.player;

import demo.player.messenger.Messenger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Player {
	private final static int MAX_SEND_MESSAGES = 3;
	private final String name;
	private final Messenger messenger;
	private int sentCounter;
	private final ScheduledFuture<?> scheduledFuture;

	public Player(String name, Messenger messenger) {
		this.name = name;
		this.messenger = messenger;
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduledFuture = scheduler.scheduleAtFixedRate(this::getMessage, 1000, 100, TimeUnit.MILLISECONDS);
	}

	private void getMessage() {
		MessageDto messageDto = messenger.getMessage(name);
		if (messageDto != null) {
			System.out.println("My name is " + name + ", I got a message " + messageDto);
			sendMessage(messageDto.from, messageDto.text);
		}
	}

	public void sendMessage(String to, String text) {
		if (sentCounter >= MAX_SEND_MESSAGES) {
			scheduledFuture.cancel(false);
			return;
		}
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