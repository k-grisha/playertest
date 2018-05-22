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
import java.util.concurrent.LinkedBlockingQueue;

public class MessengerServerImpl implements MessengerServer {

	private Map<String, LinkedBlockingQueue<MessageDto>> playersMessages = new HashMap<>();

	@Override
	public void sendMessage(MessageDto messageDto) {
		LinkedBlockingQueue<MessageDto> messages = playersMessages.computeIfAbsent(messageDto.to, k -> new LinkedBlockingQueue<>());
		try {
			messages.put(messageDto);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MessageDto getMessage(String name) {
		LinkedBlockingQueue<MessageDto> messages = playersMessages.get(name);
		return messages != null ? messages.poll() : null;
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
