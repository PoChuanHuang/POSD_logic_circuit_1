package com.shape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LogicSimulator {
	public static String classname = "LogicSimulator";
	static ArrayList<CircuitMap> circuit = new ArrayList<>();
//	static ArrayList<Vector> oPins       = new ArrayList<>();
	
	public static Boolean load(String FilePath) throws IOException {
		circuit.clear();
		/*iPins.clear();*/
//		oPins.clear();
		FileReader fr      = new FileReader(FilePath);
        BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(FilePath), "UTF8"));
        String load_content = "";
		String line_text    = "";
		while((line_text=bfr.readLine())!=null)
		{load_content += line_text + "\n";}
		fr.close();bfr.close();
		
		String[] load_content_split_line = load_content.split("\n");
		Main.circuit_input_pins_count = Integer.valueOf(load_content_split_line[0]);
		Main.circuit_gates_count      = Integer.valueOf(load_content_split_line[1]);
		int old_gate = 0;
		for(int index=2; index < load_content_split_line.length; index++) {
			String[] load_content_split_line_item = load_content_split_line[index].split(" ");
			int gateType = Integer.valueOf(load_content_split_line_item[0]);
			int gate     = index-1;
			GateVector gateVector = new GateVector();
			for(int i =1;i<load_content_split_line_item.length-1; i++) {
				
				if(old_gate != gate) {
//					gateVector = new GateVector(gate, gateType, 1);
					gateVector.gate       = gate;
					gateVector.gateType   = gateType;
					gateVector.inputCount = 1;
				}else {
					gateVector.addinputCount(1);
				}
				if(load_content_split_line_item[i].indexOf("-") != -1) {
					int iPinST         = Integer.valueOf(load_content_split_line_item[i].replace("-",""));
					CircuitMap iPinMap = new CircuitMap(gate, gateType, 0, iPinST, -1, gateVector);/*iPinsMap(int gateType , int inputType , int input)*/
					circuit.add(iPinMap);
				}else if(load_content_split_line_item[i].indexOf(".") != -1){
					int gateST         = Integer.valueOf(load_content_split_line_item[i].substring(0,1));
					CircuitMap iPinMap = new CircuitMap(gate, gateType, 1, -1, gateST, gateVector);/*iPinsMap(int gateType , int inputType , int input)*/
					circuit.add(iPinMap);
				}

				old_gate = gate;
			}
		}
		return true;
	};
	
	
	public static ArrayList<ArrayList<String>> getSimulationResult() {
        /**
         * i i i | o 
         * 1 2 3 | 1 
         * ------+-- 
         * 0 1 1 | 0
         */
		
		int result = Device.iPin();
        ArrayList<ArrayList<String>> result2Array = new ArrayList<ArrayList<String>>();
        ResultTitle(result2Array);
        result2Array.add(new ArrayList<>());
        for(int i=0;i<Device.iPins.size();i++) {
        	result2Array.get(3).add(String.valueOf(Device.iPins.get(i)));
        }
        result2Array.get(3).add("|");
        result2Array.get(3).add(String.valueOf(result));
		return result2Array;
	}
	public static void ResultTitle(ArrayList<ArrayList<String>> result2Array) {
        for(int i=0;i<Main.circuit_input_pins_count;i++) {
        	result2Array.add(new ArrayList<>());
        }
        
        for(int i=0;i<Main.circuit_input_pins_count;i++) {
        	result2Array.get(0).add("i");
        	result2Array.get(1).add( String.valueOf(i+1) );
        	result2Array.get(2).add( "-" );
        }

        result2Array.get(0).add("|");result2Array.get(0).add("o");
        result2Array.get(1).add("|");result2Array.get(1).add("1");
        result2Array.get(2).add("+");result2Array.get(2).add("-");
	}

}
