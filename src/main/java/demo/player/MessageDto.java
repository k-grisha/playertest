package demo.player;

public class MessageDto {
	public final String from;
	public final String to;
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