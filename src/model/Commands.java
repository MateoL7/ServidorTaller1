package model;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;

public class Commands {
	
	public Commands() {
		
	}

	public String getIP() {
		String ip = "";
		
		try {
			InetAddress myAddress = InetAddress.getLocalHost();
			ip = myAddress.getHostAddress();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ip;
	}
	public String getTime() {
		String time = "";
		Calendar c = Calendar.getInstance();
		time = c.getTime().toString();
		return time;
	}
	public String getInterface() {
		String inter = "";
		InetAddress myAddress;
		try {
			myAddress = InetAddress.getLocalHost();
			NetworkInterface net = NetworkInterface.getByInetAddress(myAddress);
			inter = net.getName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inter;
	}
}
