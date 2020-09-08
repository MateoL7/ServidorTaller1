package app;
import comm.ServidorT1;
import model.Commands;

import java.util.Scanner;

import comm.Emisor;
import comm.Receptor.OnMessageListener;

//OBSERVADOR
public class Application implements OnMessageListener{

	private ServidorT1 connection;
	private Commands commands;

	public Application(){
		connection = ServidorT1.getInstance();
		connection.setListenerOfMessages(this);
		connection.setPuerto(5000);
		commands = new Commands();
	}

	public void init() {
		connection.start();
	}

	@Override
	public void OnMessage(String msg) {
		String answer = "No es un comando valido";
		if(msg.equalsIgnoreCase("remoteipconfig")) {
			answer = commands.getIP();
		}else if(msg.equalsIgnoreCase("whattimeisit")) {
			answer = commands.getTime();
		}else if(msg.equalsIgnoreCase("interface")) {
			answer = commands.getInterface();
		}else if(msg.getBytes().length == 1024) {
			answer = msg;
		}else if(msg.getBytes().length == 8192) {
			answer = msg;
		}
		Emisor em = connection.getEmisor();
		if(em != null) em.sendMessage(answer);
		else System.out.println("No hay cliente conectado");
	}













	//
	//	@Override
	//	public void onUpdate(int value) {
	//		System.out.println("El poceso va por: " + value);
	//	}
	//	
	//	@Override
	//	public void onFinish() {
	//		System.out.println("El proceso finalizo");
	//		
	//	}

}


