package com.shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class TextUI {
	public String classname = "TextUI";
	Scanner scanner = new Scanner(System.in);
	public void displayMenuAndprocessCommand() {
		displayMenu();
		processCommand();
	};
	public void displayMenu() {
		System.out.print("1. Load logic circuit file \n" + 
						 "2. Simulation \n" + 
						 "3. Display truth table \n" + 
						 "4. Exit \n" + 
						 "Command:");

	};
	public ArrayList<Integer> addinputPin() {
		int pin;
		ArrayList<Integer> iPins      = new ArrayList<>();
		for(int i=0; i<Main.circuit_input_pins_count; i++) {
			System.out.print("Please key in the value of input pin " + (i+1) + ": ");
			pin = scanner.nextInt();
			if(pin==0||pin==1) {
				iPins.add(pin);
			}else {
				System.out.println("The value of input pin must be 0/1 ");
				i--;
			}
		}
		return iPins;
	}
	public void processCommand() {
		int index = scanner.nextInt();
		switch(index) {
		case 1:
			String path = "";
			System.out.print("Please key in a file path: ");
			if(Main.Debug) {
				path = "C:\\Users\\life\\eclipse-workspace\\POSD_logic_circuit\\logic_circuit.txt";
				System.out.println(path);
			}else {
				path = scanner.nextLine();
			}
			try {
				Main.loadSuccess = LogicSimulator.load(path);
				System.out.println("Circuit: " + Main.circuit_input_pins_count + " input pins, 1 output pins and " + Main.circuit_gates_count + " gates ");
				

		        displayMenuAndprocessCommand();
			} catch (IOException e) {
				System.out.print("資料載入有誤");
				Main.loadSuccess = false;
				displayMenuAndprocessCommand();
			}
			break;
		case 2:
			if(Main.loadSuccess) {
				int pin;
				for(int i=0; i<Main.circuit_input_pins_count; i++) {
					System.out.print("Please key in the value of input pin " + (i+1) + ": ");
					pin = scanner.nextInt();
					if(pin==0||pin==1) {
						Device.addinputPin(pin);
					}else {
						System.out.println("The value of input pin must be 0/1 ");
						i--;
					}
					
				}

				System.out.println("Simulation Result: ");
				LogicSimulator.getSimulationResult(Device.iPins);
				displayMenuAndprocessCommand();
			}else{
				System.out.println("Please load an lcf file, before using this operation.");
				displayMenuAndprocessCommand();
			}
			break;
		case 3:
			System.out.println("show Truth table");
			displayMenuAndprocessCommand();
			break;
		case 4:
			System.out.print("Goodbye, thanks for using LS.");
			System.exit(0);
			break;
		default:
			displayMenuAndprocessCommand();
			break;
		}
	};
	
}
