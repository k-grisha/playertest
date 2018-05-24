package demo.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class MainRemote {

	/**
	 * Start Server and Players in separate Java process
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		Process serverProc = Runtime.getRuntime().exec("java -cp target\\classes demo.player.rmi.MessengerServerImpl");
		TimeUnit.MILLISECONDS.sleep(500);
		Process receiverProc = Runtime.getRuntime().exec("java -cp target\\classes demo.player.Player AAA");
		TimeUnit.MILLISECONDS.sleep(500);
		Process initiatorProc = Runtime.getRuntime().exec("java -cp target\\classes demo.player.Player BBB AAA");
		BufferedReader br1 = new BufferedReader(new InputStreamReader(receiverProc.getInputStream()));
		BufferedReader br2 = new BufferedReader(new InputStreamReader(initiatorProc.getInputStream()));
		String line1;
		String line2;
		do {
			if ((line1 = br1.readLine()) != null) {
				System.out.println("proc1: " + line1);
			}
			if ((line2 = br2.readLine()) != null) {
				System.out.println("proc2: " + line2);
			}
		} while (line1 != null && line2 != null);
		serverProc.destroy();
	}

}
