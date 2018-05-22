package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.Player;
import demo.player.rmi.MessengerClient;
import demo.player.rmi.MessengerServer;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteMessenger implements Messenger {
	private MessengerServer messengerServer;
	private Player player;

	public RemoteMessenger() {
		try {
			Registry registry = LocateRegistry.getRegistry(null, 7070);
			messengerServer = (MessengerServer) registry.lookup("Messenger");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}


	public void registerPlayer(Player player) {
		this.player = player;
		try {
			MessengerClient stub = (MessengerClient) UnicastRemoteObject.exportObject(new MessengerClientImpl(), 0);
			Registry registry = LocateRegistry.getRegistry(null, 7070);
			registry.bind(player.getName(), stub);

			messengerServer.registerPlayer(player.getName());
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void send(MessageDto messageDto) {
		Runnable task = () -> {
			try {
				messengerServer.send(messageDto);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		};
		new Thread(task).start();
	}

	@Override
	public MessageDto getMessage(String name) {
		return null;
	}


	class MessengerClientImpl implements MessengerClient {
		@Override
		public void receiveMessage(MessageDto messageDto) throws RemoteException {
//			player.receiveMessage(messageDto);
		}
	}
}
