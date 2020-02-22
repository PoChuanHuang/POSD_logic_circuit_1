package com.shape;
import com.shape.TextUI;

public class Main {
	public static String classname = "Main";
	public static int circuit_input_pins_count = 0;
	public static int circuit_gates_count      = 0;
	public static boolean loadSuccess = false;
	public static boolean Debug = false;
	public static void main(String[] args) {
		TextUI textUI1 = new TextUI();
		textUI1.displayMenuAndprocessCommand();
	}
}
