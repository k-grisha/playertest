package demo.player;

import java.io.Serializable;

/**
 * DTO for message exchange
 */
public class MessageDto implements Serializable {
	/**
	 * Sender name
	 */
	public final String from;
	/**
	 * Receiver name
	 */
	public final String to;
	/**
	 * Text of message
	 */
	public final String text;

	public MessageDto(String from, String to, String text) {
		this.from = from;
		this.to = to;
		this.text = text;
	}

	@Override
	public String toString() {
		return "MessageDto{" +
				"from='" + from + '\'' +
				", to='" + to + '\'' +
				", text='" + text + '\'' +
				'}';
	}
}