package app;
import comm.ServidorT1;
import model.Commands;

//OBSERVADOR
public class Application implements ServidorT1.OnMessageListener{

	private ServidorT1 connection;
	private Commands commands;

	public Application(){
		connection = ServidorT1.getInstance();
		connection.setListener(this);
		connection.setPuerto(5000);
		commands = new Commands();
	}

	public void init() {
		connection.start();
	}

	@Override
	public String OnMessage(String msg) {
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
		return answer;
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


