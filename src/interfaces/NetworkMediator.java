package interfaces;

import java.io.IOException;

public interface NetworkMediator {

	public void send(String message) throws IOException;
	public String receive() throws IOException;
}
