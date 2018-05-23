package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.rmi.MessengerServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiMessenger extends LocalMessenger implements MessengerServer {

	@Override
	public MessageDto getMessage(String playerName) {
		return super.getMessage(playerName);
	}

	@Override
	public void sendMessage(MessageDto messageDto) {
		super.sendMessage(messageDto);
	}

	public static void main(String[] args) {
		RmiMessenger rmiMessenger = new RmiMessenger();
		try {
			MessengerServer stub = (MessengerServer) UnicastRemoteObject.exportObject(rmiMessenger, 0);
			Registry registry = LocateRegistry.createRegistry(7070);
			registry.bind("RmiMessenger", stub);
			System.out.println("RmiMessenger started");
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

}
