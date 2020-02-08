package com.shape;

import java.io.IOException;
import java.util.Scanner;
import com.shape.loadFile;
public class TextUI {
	public void displayMenu() {
		System.out.print("1. Load logic circuit file \n" + 
						 "2. Simulation \n" + 
						 "3. Display truth table \n" + 
						 "4. Exit \n" + 
						 "Command:");
	};
	public void processCommand(int index) {
		switch(index) {
		case 1:
			System.out.print("Please key in a file path: ");
			Scanner scanner = new Scanner(System.in);
			String path = scanner.nextLine();
			/*C:\Users\life\eclipse-workspace\POSD_HWt\src\com\shape\logic_circuit.txt*/
			try {
				loadFile.LoadDatabase(path);
			} catch (IOException e) {
				System.out.print("資料載入有誤");
			}
			break;
		case 2:
			System.out.print("Please key in the value of input pin 1: ");
			break;
		}
	};
}
