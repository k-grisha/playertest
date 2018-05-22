package demo.player.rmi;

import demo.player.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerClient  extends Remote {
	void receiveMessage(MessageDto messageDto) throws RemoteException;
}
