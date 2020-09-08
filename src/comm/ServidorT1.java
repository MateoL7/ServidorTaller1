package comm;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import comm.Receptor.OnMessageListener;

//CLASE OBSERVADA

public class ServidorT1 extends Thread {


	//SINGLETON
	private static ServidorT1 instance;
	private ServidorT1() {

	}
	public static synchronized ServidorT1 getInstance() {
		if(instance == null) {
			instance = new ServidorT1();
		}
		return instance;
	}



	private ServerSocket server;
	private Socket socket;
	private int puerto;
	private Emisor emisor;
	private Receptor receptor;
	public OnMessageListener listener;

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(puerto);
			System.out.println("Esperando cliente");
			socket = server.accept();
			System.out.println("Cliente Conectado");
			
			receptor = new Receptor(socket.getInputStream());
			receptor.setListener(listener);
			receptor.start();
			
			emisor = new Emisor(socket.getOutputStream());


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setListenerOfMessages(OnMessageListener listener) {
		 this.listener = listener;
	}
	
	public Emisor getEmisor() {
		return this.emisor;
	}

}
