package com.shape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

public class LogicSimulator {
	public static String classname = "LogicSimulator";
	static ArrayList<CircuitMap> circuit = new ArrayList<>();
	/*static ArrayList<Integer> iPins      = new ArrayList<>();*/
	static ArrayList<Vector> oPins       = new ArrayList<>();
	
	public static Boolean load(String FilePath) throws IOException {
		circuit.clear();
		/*iPins.clear();*/
		oPins.clear();
		FileReader fr      = new FileReader(FilePath);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath), "UTF8"));
        String load_content = "";
		String line_text    = "";
		while((line_text=bfr.readLine())!=null)
		{load_content += line_text + "\n";}
		fr.close();bfr.close();
		
		String[] load_content_split_line = load_content.split("\n");
		Circuit.circuit_input_pins_count = Integer.valueOf(load_content_split_line[0]);
		Circuit.circuit_gates_count      = Integer.valueOf(load_content_split_line[1]);
		for(int index=2; index < load_content_split_line.length; index++) {
			String[] load_content_split_line_item = load_content_split_line[index].split(" ");
			int gatetype = Integer.valueOf(load_content_split_line_item[0]);
			
			for(int i =1;i<load_content_split_line_item.length-1; i++) {
				if(load_content_split_line_item[i].indexOf("-") != -1) {
					int input        = Integer.valueOf(load_content_split_line_item[i].replace("-",""));
					CircuitMap iPinMap = new CircuitMap(gatetype , 0 , input);/*iPinsMap(int gateType , int inputType , int input)*/
					circuit.add(iPinMap);
				}else if(load_content_split_line_item[i].indexOf(".") != -1){
					int input        = Integer.valueOf(load_content_split_line_item[i].substring(0,1));
					CircuitMap iPinMap = new CircuitMap(gatetype , 1 , input);/*iPinsMap(int gateType , int inputType , int input)*/
					circuit.add(iPinMap);
				}
			}
		}
		return true;
	};
	
	
	
	public static String getSimulationResult(ArrayList<Integer> iPins) {
		String Result="";
		for(int i=0;i< iPins.size(); i++) {
			System.out.println("[" + classname + "]=>LogicSimulator.iPins : " + iPins.get(i));
		}
		return Result;
	}

}
