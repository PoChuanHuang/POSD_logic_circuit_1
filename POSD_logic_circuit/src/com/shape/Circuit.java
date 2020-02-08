package com.shape;
import java.util.Scanner;
import com.shape.TextUI;

public class Circuit {
	public static int circuit_input_pins_count = 0;
	public static int circuit_gates_count      = 0;
	public static boolean loadSuccess = false;
	public static boolean Debug = true;
	public static void main(String[] args) {
		TextUI textUI1 = new TextUI();
		textUI1.displayMenu();
        
        /*System.out.println("[Circuit]=>v(circuit_input_pins_count) = " + circuit_input_pins_count);
        System.out.println("[Circuit]=>v(circuit_gates_count) = " + circuit_gates_count);
        for(int i=0; i<LogicSimulator.iPins.size(); i++) {
        	System.out.print("[Circuit]=>v(LogicSimulator.iPins) = " + LogicSimulator.iPins.get(i).gateType);        	
        	System.out.print("," + LogicSimulator.iPins.get(i).inputType);        	
        	System.out.println("," + LogicSimulator.iPins.get(i).input);        	
        }*/
	}
}
