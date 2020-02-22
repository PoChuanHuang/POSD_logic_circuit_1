package com.shape;

import java.util.ArrayList;
import java.util.List;
/**
 * �Ĥ@��iPin=0 �M�� gate���s��iPinST=1 inputType=0 ��o��iPin=0 ��igate���ܼƸ�
 * �ĤG��iPin=1 �M�� gate���s��iPinST=2 inputType=0 ��o��iPin=1 ��igate���ܼƸ�
 * �ĤT��iPin=0 �M�� gate���s��iPinST=3 inputType=0 ��o��iPin=0 ��igate���ܼƸ�
 * iPin() �B�zfor iPins �ĴX��iPin=X �M�� iPinST=X inputType=0 ���ȩ�i���P��gate��inputArray��
 * --��
	 * |  �{�b�C��gate�����@��input(�qiPins�����ǨӪ�) ���۴M��gate inputValueArray.length() >= inputCount �� outputValueArray�٬O�Ū�
	 * |  ���ҷ|���gate:A(gateType:AA)
	 * |  ��gate:A���Ҧ��w�s��input��igate(ANDORNOT) �i��B�� �o�X�@��output(���D����@��output) ��boutputValueArray
	 * |  ���۬d��Circuit���S��gateST=A inputType=1��gate ���gate:B �� gate:C �A�qgate:A ��outputValueArray�̪��ȥ�igate:B ��gate:C ��inputValueArray 
 * |�����`��
 * �̫�䤣��gate�� �X�h�`��
 * ��̫�@��gate �� outputValueArray���� �N�Oresult
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
		//iPin() �B�zfor iPins ��i��iPin �M�� iPinST=i inputType=0 ���ȩ�i���P��gate��inputArray��
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
		//�{�b�C��gate�����@��input(�qiPins�����ǨӪ�) ���۴M��gate inputValueArray.length() >= inputCount �� outputValueArray�٬O�Ū�
        for(int i=0; i<LogicSimulator.circuit.size(); i++) {
        	CircuitMap circutMap  = LogicSimulator.circuit.get(i);
        	GateVector gateVector =  circutMap.gateVector;
        	if(gateVector.getinputValueArrayLength() >= gateVector.inputCount && gateVector.outputCount == 0) {
        		//���gate:A(gateType:AA)
        		//��gate:A ���Ҧ��w�s��input��igate(ANDORNOT) �i��B�� �o�X�@��output(���D����@��output) ��boutputValueArray
        		int result = -1;
        		switch (circutMap.gateType) {
        		case 1:result = gateAND(gateVector.inputValueArray);break;
        		case 2:result = gateOR(gateVector.inputValueArray); break;
        		case 3:result = gateNot(gateVector.inputValueArray);break;
        		default:System.out.println("�����쥼�w�q��Gate����");break;
        		}
        		gateVector.addoutputValueArray(result);
        		gateVector.addoutputCount(1);
        		finalcircutMap = circutMap;
        		//���۬d��Circuit���S��gateST=A inputType=1��gate ���gate:B �� gate:C �A�qgate:A ��outputValueArray�̪��ȥ�igate:B ��gate:C ��inputValueArray 
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
