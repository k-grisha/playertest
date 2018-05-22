package demo.player.rmi;

import demo.player.MessageDto;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class MessengerServerImpl implements MessengerServer {

	private Map<String, MessengerClient> players = new HashMap<>();

	@Override
	public void registerPlayer(String playerName) throws RemoteException {
		System.out.println("register new user " + playerName);
		Registry registry = LocateRegistry.getRegistry(null, 7070);

		try {
			MessengerClient messengerClient = (MessengerClient) registry.lookup(playerName);
			players.put(playerName, messengerClient);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(MessageDto messageDto) throws RemoteException {
		System.out.println("sen new msg " + messageDto);
		players.get(messageDto.to).receiveMessage(messageDto);
	}

	public static void main(String[] args) {
		MessengerServerImpl messengerServer = new MessengerServerImpl();
		try {
			MessengerServer stub = (MessengerServer) UnicastRemoteObject.exportObject(messengerServer, 0);
			Registry registry = LocateRegistry.createRegistry(7070);
			registry.bind("Messenger", stub);

			System.out.println("MessengerServer started");

		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
