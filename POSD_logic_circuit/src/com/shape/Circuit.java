package com.shape;
import java.util.Scanner;
import com.shape.TextUI;

public class Circuit {
	public static void main(String[] args) {
		TextUI textUI1 = new TextUI();
		textUI1.displayMenu();
		int index;
        Scanner scanner = new Scanner(System.in);
        index = scanner.nextInt();
        textUI1.processCommand(index);
	}
}
