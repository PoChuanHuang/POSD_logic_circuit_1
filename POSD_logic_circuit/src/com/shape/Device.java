package com.shape;

import java.util.ArrayList;
import java.util.List;
/**
 * 第一個iPin=0 尋找 gate有連到iPinST=1 inputType=0 把這個iPin=0 放進gate的變數裡
 * 第二個iPin=1 尋找 gate有連到iPinST=2 inputType=0 把這個iPin=1 放進gate的變數裡
 * 第三個iPin=0 尋找 gate有連到iPinST=3 inputType=0 把這個iPin=0 放進gate的變數裡
 * iPin() 處理for iPins 第幾個iPin=X 尋找 iPinST=X inputType=0 此值放進不同的gate的inputArray裡
 * --↓
	 * |  現在每個gate都有一些input(從iPins直接傳來的) 接著尋找gate inputValueArray.length() >= inputCount 但 outputValueArray還是空的
	 * |  此例會找到gate:A(gateType:AA)
	 * |  把gate:A的所有已存的input丟進gate(ANDORNOT) 進行運算 得出一個output(此題限制一個output) 放在outputValueArray
	 * |  接著查看Circuit有沒有gateST=A inputType=1的gate 找到gate:B 跟 gate:C 再從gate:A 的outputValueArray裡的值丟進gate:B 跟gate:C 的inputValueArray 
 * |←←循環
 * 最後找不到gate時 出去循環
 * 抓最後一個gate 的 outputValueArray的值 就是result
 */
public class Device {
	public static String classname  = "Device";
	static ArrayList<Integer> iPins = new ArrayList<>();

	public static void addinputPin(int pin) {
		iPins.add(pin);
	}
	public static void CleariPin() {
		iPins.clear();
		for(int i=0;i< LogicSimulator.circuit.size();i++) {
			LogicSimulator.circuit.get(i).gateVector.ClearGateVector();
		}
	}
	public static int iPin() {

		CircuitMap finalcircutMap = new CircuitMap();
		//iPin() 處理for iPins 第i個iPin 尋找 iPinST=i inputType=0 此值放進不同的gate的inputArray裡
		for(int i=0;i< Device.iPins.size(); i++) {
	        for(int j=0; j<LogicSimulator.circuit.size(); j++) {
	        	if(LogicSimulator.circuit.get(j).inputType==0) {
	        		if((i+1) == LogicSimulator.circuit.get(j).iPinST) {
	        			LogicSimulator.circuit.get(j).gateVector.addinputValueArray(Device.iPins.get(i));
		        	}
	        	}
	        }
		}
		for(int i=0; i<LogicSimulator.circuit.size(); i++) {
        	CircuitMap circutMap  = LogicSimulator.circuit.get(i);
        	GateVector gateVector = circutMap.gateVector;
        	if(gateVector.outputCount == 0) {
        		finalcircutMap = oPin(finalcircutMap);
        	}
		}
//        Show(finalcircutMap);
//        System.out.println("result = " + finalcircutMap.gateVector.getoutputValueArrayItem(0));
        return finalcircutMap.gateVector.getoutputValueArrayItem(0);
	}

	public static CircuitMap oPin(CircuitMap finalcircutMap) {
		//現在每個gate都有一些input(從iPins直接傳來的) 接著尋找gate inputValueArray.length() >= inputCount 但 outputValueArray還是空的
        for(int i=0; i<LogicSimulator.circuit.size(); i++) {
        	CircuitMap circutMap  = LogicSimulator.circuit.get(i);
        	GateVector gateVector =  circutMap.gateVector;
        	if(gateVector.getinputValueArrayLength() >= gateVector.inputCount && gateVector.outputCount == 0) {
        		//找到gate:A(gateType:AA)
        		//把gate:A 的所有已存的input丟進gate(ANDORNOT) 進行運算 得出一個output(此題限制一個output) 放在outputValueArray
        		int result = -1;
        		switch (circutMap.gateType) {
        		case 1:result = gateAND(gateVector.inputValueArray);break;
        		case 2:result = gateOR(gateVector.inputValueArray); break;
        		case 3:result = gateNot(gateVector.inputValueArray);break;
        		default:System.out.println("偵測到未定義的Gate類型");break;
        		}
        		gateVector.addoutputValueArray(result);
        		gateVector.addoutputCount(1);
        		finalcircutMap = circutMap;
        		//接著查看Circuit有沒有gateST=A inputType=1的gate 找到gate:B 跟 gate:C 再從gate:A 的outputValueArray裡的值丟進gate:B 跟gate:C 的inputValueArray 
        		for(int j=0; j<LogicSimulator.circuit.size(); j++) {
        			CircuitMap circutMap2  = LogicSimulator.circuit.get(j);
                	GateVector gateVector2 =  circutMap2.gateVector;
        			if(circutMap2.gateST == circutMap.gate && circutMap2.inputType == 1) {
        				int newInputValue = gateVector.getoutputValueArrayItem(0);
        				gateVector2.addinputValueArray(newInputValue);
        			}
        		}
        	}
        }
        return finalcircutMap;
	}
	public static int gateNot(List<Integer> inputArray) {
		int input = inputArray.get(0);
		switch (input) {
		case 0:return 1;
		case 1:return 0;
		default:return -1;
		}
	}
	public static int gateAND(List<Integer> inputArray) {
		int result;
		int total = 0;
		for(int element : inputArray) { total += element; }
		if(total != inputArray.size()) {result = 0;}
		else {result = 1;}
		return result;
	}
	public static int gateOR(List<Integer> inputArray) {
		int result;
		int total = 0;
		for(int element : inputArray) {total += element; }
		if(total == 0) {result = 0;}
		else {result = 1;}
		return result;
	}
	
	/*public static void Show(CircuitMap finalcircutMap) {
		String [] attribute = new String[]{"gate", "gateType" , "inputType" , "iPinST" , "gateST", "inputCount" , "inputValueArray"};
		System.out.println("["+classname+"] =>");      	
		System.out.format("%s %s %s %s %s %s %s" , attribute[0] , attribute[1] , attribute[2] , attribute[3] , attribute[4] , attribute[5] , attribute[6]);
		System.out.println();
		String dataformat = "";
		for(int i=0;i<attribute.length;i++) {dataformat += "%" + attribute[i].length() + "s "; }
        for(int i=0; i<LogicSimulator.circuit.size(); i++) {
        	String inputValueArrayString = "";
        	for(int j=0;j<LogicSimulator.circuit.get(i).gateVector.getinputValueArrayLength();j++) {
        		inputValueArrayString += LogicSimulator.circuit.get(i).gateVector.inputValueArray.get(j) + " ";
        	}
            System.out.format(dataformat ,
            		LogicSimulator.circuit.get(i).gate, 
            		LogicSimulator.circuit.get(i).gateType, 
            		LogicSimulator.circuit.get(i).inputType, 
            		LogicSimulator.circuit.get(i).iPinST, 
            		LogicSimulator.circuit.get(i).gateST,
            		LogicSimulator.circuit.get(i).gateVector.inputCount,
            		inputValueArrayString
            		);
            System.out.println();
        }
        //System.out.println("result = " + finalcircutMap.gateVector.getoutputValueArrayItem(0));
    
	}*/
}
