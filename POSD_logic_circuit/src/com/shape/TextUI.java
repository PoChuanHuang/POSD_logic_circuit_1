package com.shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import com.shape.loadFile;
public class TextUI {
	public String classname = "TextUI";
	public void displayMenu() {
		System.out.print("1. Load logic circuit file \n" + 
						 "2. Simulation \n" + 
						 "3. Display truth table \n" + 
						 "4. Exit \n" + 
						 "Command:");
		Scanner scanner = new Scanner(System.in);
		int index = scanner.nextInt();
        processCommand(index);
	};
	public void processCommand(int index) {
		switch(index) {
		case 1:
			String path = "";
			System.out.print("Please key in a file path: ");
			if(Circuit.Debug) {
				path = "C:\\Users\\life\\eclipse-workspace\\POSD_logic_circuit\\logic_circuit.txt";
				System.out.println(path);
			}else {
				Scanner scanner = new Scanner(System.in);
				path = scanner.nextLine();
			}
			try {
				Circuit.loadSuccess = LogicSimulator.load(path);
				System.out.println("Circuit: " + Circuit.circuit_input_pins_count + " input pins, 1 output pins and " + Circuit.circuit_gates_count + " gates ");
				
		        for(int i=0; i<LogicSimulator.circuit.size(); i++) {
		        	System.out.print("["+classname+"]=>v(LogicSimulator.iPins) = " + LogicSimulator.circuit.get(i).gateType);        	
		        	System.out.print(","   + LogicSimulator.circuit.get(i).inputType);        	
		        	System.out.println("," + LogicSimulator.circuit.get(i).input);        	
		        }
		        displayMenu();
			} catch (IOException e) {
				System.out.print("資料載入有誤");
				Circuit.loadSuccess = false;
				displayMenu();
			}
			break;
		case 2:
			if(Circuit.loadSuccess) {
				int pin;
				ArrayList<Integer> iPins      = new ArrayList<>();
				for(int i=0; i<Circuit.circuit_input_pins_count; i++) {
					System.out.print("Please key in the value of input pin " + (i+1) + ": ");
					Scanner scanner = new Scanner(System.in);
					pin = scanner.nextInt();
					if(pin==0||pin==1) {
						iPins.add(pin);
					}else {
						System.out.println("The value of input pin must be 0/1 ");
						i--;
					}
					
				}

				System.out.println("Simulation Result: ");
				LogicSimulator.getSimulationResult(iPins);
			}else{
				
				System.out.println("Please load an lcf file, before using this operation.");
				displayMenu();
			}
			break;
		case 3:
			System.out.println("show Truth table");
			displayMenu();
			break;
		case 4:
			System.out.print("Goodbye, thanks for using LS.");
			System.exit(0);
		}
	};
	
}
