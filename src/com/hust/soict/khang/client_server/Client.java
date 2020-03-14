package com.hust.soict.khang.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 1503);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			System.out.println("Nhap cac so can sap xep");
			System.out.println(in.readLine());
			Scanner scanner = new Scanner(System.in);
			boolean userChat = true;
			
			while(userChat) {
				String mess = scanner.nextLine();
				if(mess == "") {
					userChat = false;
				}else {
					out.println(mess);
				}
				System.out.println(in.readLine());
				// send to server
			}
			socket.close();
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
