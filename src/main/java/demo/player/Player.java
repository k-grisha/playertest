package demo.player;

import demo.player.messenger.Messenger;
import demo.player.messenger.RemoteMessenger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Player
 */
public class Player {
	private final static int MAX_MESSAGES = 10;
	private final String name;
	private final Messenger messenger;
	private int sentCounter;
	private int receiveCounter;
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public Player(String name, Messenger messenger) {
		this.name = name;
		this.messenger = messenger;
		// Scheduled check incoming messages
		scheduler.scheduleAtFixedRate(this::getMessage, 1000, 200, TimeUnit.MILLISECONDS);
	}

	// check incoming messages
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

	/**
	 * Send Message
	 *
	 * @param to   receiver name
	 * @param text text of message
	 */
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

	/**
	 * Method for implementing Player in separate process
	 *
	 * @param args playerName [opponentName]
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("New Player name missed");
			return;
		}
		Messenger messenger = new RemoteMessenger();
		Player player = new Player(args[0], messenger);
		if (args.length > 1) {
			player.sendMessage(args[1], "HelLo!");
		}
	}
}