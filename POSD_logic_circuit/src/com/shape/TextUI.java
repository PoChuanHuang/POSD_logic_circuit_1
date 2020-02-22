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
				scanner = new Scanner(System.in);
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
				Device.CleariPin();
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
				ArrayList<ArrayList<String>> resultArray = LogicSimulator.getSimulationResult();
				for(int i=0; i<resultArray.size(); i++) {
					for(int j=0; j<resultArray.get(i).size(); j++) {
						System.out.print( resultArray.get(i).get(j) +"\t");
					}
					System.out.println();
				}
				displayMenuAndprocessCommand();
			}else{
				System.out.println("Please load an lcf file, before using this operation.");
				displayMenuAndprocessCommand();
			}
			break;
		case 3:
			System.out.println("show Truth table");
			int truthTableResultCount = (int) Math.pow(2,Main.circuit_input_pins_count);
			ArrayList<ArrayList<String>> result2Array = new ArrayList<ArrayList<String>>();
			LogicSimulator.ResultTitle(result2Array);
			for (int i=0; i<truthTableResultCount; i++) {
				Device.CleariPin();
	            for (int j=Main.circuit_input_pins_count-1; j>=0; j--) {
	            	int pin = (i/(int) Math.pow(2, j))%2;
	            	Device.addinputPin(pin);
	            }
	    		int result = Device.iPin();
	    		result2Array.add(new ArrayList<>());
	            for(int j=0;j<Device.iPins.size();j++) {
	            	result2Array.get(i+3).add(String.valueOf(Device.iPins.get(j)));
	            }
	            result2Array.get(i+3).add("|");
	            result2Array.get(i+3).add(String.valueOf(result));
	        }
			for(int i=0; i<result2Array.size(); i++) {
				for(int j=0; j<result2Array.get(i).size(); j++) {
					System.out.print( result2Array.get(i).get(j) +"\t");
				}
				System.out.println();
			}
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
