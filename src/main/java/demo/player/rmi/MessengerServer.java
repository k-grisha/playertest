package demo.player.rmi;

import demo.player.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for Remote Method Invocation
 */
public interface MessengerServer extends Remote {

	MessageDto getMessage(String playerName) throws RemoteException;

	void sendMessage(MessageDto messageDto) throws RemoteException;
}