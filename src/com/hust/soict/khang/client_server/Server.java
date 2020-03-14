package com.hust.soict.khang.client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import com.hust.soict.khang.helper.*;
import java.util.Arrays;

public class Server {
	public static void main(String args[]) {
		System.out.println("The Sorter Server is running!");
		int clientNumber = 0;
		try (ServerSocket listener = new ServerSocket(1503)) {
			while (true) {
				new Sorter(listener.accept(), clientNumber++).start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
class Sorter extends Thread {
	private Socket socket;
	private int clientNumber;
	public Sorter(Socket socket, int clientNumber) {
		this.socket= socket;
		this.clientNumber= clientNumber;
		System.out.println("New client #"+ clientNumber+ " connected at "+ socket);
	}

public void run() {
	try{
		BufferedReader in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out= new PrintWriter(socket.getOutputStream(), true);// Send a welcome message to the client.
		out.println("Hello, you are client #"+ clientNumber);
		// Get messages from the client, line by line; Each line has several numbers separated by a space character
		while(true) {
			String input= in.readLine();
			System.out.println("Nhan dc: " + input);
			if(input== null|| input.isEmpty()) {break;}
			//Put it in a string array
			String[] nums= input.split(" ");//Convert this string array to an intarray
			int[] intarr= new int[ nums.length];
			int i= 0; 
			for( String textValue: nums) {
				intarr[i] = Integer.parseInt( textValue); i++; 
				} 
			// Sort the numbers in this intarray by Selection sort
			// new SelectionSort().sort(intarr);
			// Bubble Sort
			// new BubbleSort().sort(intarr);
			// Insertion Sort
			new InsertionSort().sort(intarr);
			// Shell Sort
			new ShellSort().sort(intarr);
			//Convert the intarray to String
			String strArray[] = Arrays.stream(intarr).mapToObj(String::valueOf).toArray(String[]::new);
			//Send the result to Client
			out.println(Arrays.toString(strArray));
			}
		} catch(IOException e) {
			System.out.println("Error handling client #"+ clientNumber);
			}
		finally{
			try{
				socket.close();
				} 
			catch(IOException e) {}
			System.out.println("Connection with client # "+ clientNumber+ " closed");
			}
	}
}