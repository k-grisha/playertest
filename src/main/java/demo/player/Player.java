package demo.player;

class Player {
	private final String name;
	private final Messenger messenger;
	private int sentCounter;

	public Player(String name, Messenger messenger) {
		this.name = name;
		this.messenger = messenger;
	}

	public void receiveMessage(MessageDto messageDto) {
		System.out.println("My name is " + name + ", I got a message " + messageDto);
		sendMessage(messageDto.from, messageDto.text);
	}

	public void sendMessage(String to, String text) {
		if (sentCounter < 3) {
			messenger.send(new MessageDto(name, to, text + String.format("%02d", sentCounter)));
			sentCounter++;
		}
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