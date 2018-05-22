package demo.player.rmi;

import demo.player.MessageDto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerServer extends Remote {

	void registerPlayer(String playerName) throws RemoteException;

	void send(MessageDto messageDto) throws RemoteException;
}