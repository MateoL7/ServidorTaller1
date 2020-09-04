package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

//CLASE OBSERVADA

public class ServidorT1 extends Thread {


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
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			OutputStream out = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

			while(true) {

				String msg = br.readLine();
				String answer = listener.OnMessage(msg);
				bw.write(answer + "\n");
				bw.flush();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}

	public interface OnMessageListener{
		public String OnMessage(String msg);
	}

}
