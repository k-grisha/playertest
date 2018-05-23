package demo.player.rmi;

import demo.player.MessageDto;
import demo.player.messenger.LocalMessenger;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MessengerServerImpl implements MessengerServer {

	private LocalMessenger localMessenger = new LocalMessenger();

	@Override
	public void sendMessage(MessageDto messageDto) {
		localMessenger.sendMessage(messageDto);
	}

	@Override
	public MessageDto getMessage(String name) {
		return localMessenger.getMessage(name);
	}

	public static void main(String[] args) {
		MessengerServerImpl messengerServer = new MessengerServerImpl();
		try {
			MessengerServer stub = (MessengerServer) UnicastRemoteObject.exportObject(messengerServer, 0);
			Registry registry = LocateRegistry.createRegistry(7070);
			registry.bind("MessengerServer", stub);

			System.out.println("MessengerServer started");

		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
