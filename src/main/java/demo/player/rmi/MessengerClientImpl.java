package demo.player.rmi;

import demo.player.MessageDto;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MessengerClientImpl implements MessengerClient {
	@Override
	public void receiveMessage(MessageDto messageDto) throws RemoteException {
		System.out.println("client recive msg " + messageDto);
	}

	public static void main(String[] args) {
//		MessengerClientImpl messengerClient = new MessengerClientImpl();
//		try {
//			Registry registry = LocateRegistry.getRegistry(null, 7070);
//			MessengerServer messenger = (MessengerServer)registry.lookup("Messenger");
//			messenger.getMessage("GRIII");
//
//		} catch (RemoteException | NotBoundException e) {
//			e.printStackTrace();
//		}
	}
}
