package delivery;

import java.util.List;

public abstract class MessageThread extends Thread implements MessageList{
	public List<String> getMessages() {
		return null;
	}
}
