package demo.player.messenger;

import demo.player.MessageDto;
import demo.player.rmi.MessengerClient;
import demo.player.rmi.MessengerServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteMessenger implements Messenger {
	private MessengerServer messengerServer;

	public RemoteMessenger() {
		try {
			Registry registry = LocateRegistry.getRegistry(null, 7070);
			messengerServer = (MessengerServer) registry.lookup("MessengerServer");
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void sendMessage(MessageDto messageDto) {
		Runnable task = () -> {
			try {
				messengerServer.sendMessage(messageDto);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		};
		new Thread(task).start();
	}

	@Override
	public MessageDto getMessage(String name) {
		try {
			return messengerServer.getMessage(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}


	class MessengerClientImpl implements MessengerClient {
		@Override
		public void receiveMessage(MessageDto messageDto) throws RemoteException {
//			player.receiveMessage(messageDto);
		}
	}
}
