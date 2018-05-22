package demo.player;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MessengerTest {

	@Test
	public void baseTest() throws IOException, InterruptedException {

		Process p = Runtime.getRuntime().exec("cmd /c dir");
//		Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "dir"});
//		Process p = new ProcessBuilder("cmd", "/c", "dir").start() ;
		InputStream is = p.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;

		System.out.printf("Output of running is:");

		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		p.waitFor(3, TimeUnit.SECONDS);
		p.destroy();
	}
}