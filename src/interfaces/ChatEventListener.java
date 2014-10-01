package interfaces;

public interface ChatEventListener {

	public void onChatMessageReceived(String username, String message);
}
