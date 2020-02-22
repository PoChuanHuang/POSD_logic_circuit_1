package com.shape;

import java.util.ArrayList;
import java.util.List;

public class GateVector {
	public int gate;            /** �ĴX��gate*/
	public int gateType;        /** 1: AND, 2: OR, 3: NOT*/
	public int inputCount  =0;  /** ���X��input*/
	public int outputCount =0 ; /** ���X��output*/
	public List<Integer> inputValueArray = new ArrayList<>(); /** ��gate��input�� */
	public List<Integer> outValueArray   = new ArrayList<>(); /** ��gate��output�� */
	public GateVector(){
		super();
	}
	public GateVector(int gate, int gateType, int inputCount){
		super();
		this.gate       = gate;
		this.gateType   = gateType;
		this.inputCount = inputCount;
	}
	public void addinputCount(int inputCount) {
		this.inputCount = this.inputCount + inputCount;
	}
	public void addoutputCount(int outputCount) {
		this.outputCount = this.outputCount + outputCount;
	}
	public void addinputValueArray(int inputValue) {
		this.inputValueArray.add(inputValue);
	}
	public void addoutputValueArray(int outputValue) {
		
		this.outValueArray.add(outputValue);
	}
	public int getinputValueArrayLength() {
		return inputValueArray.size();
	}

	public int getinputValueArrayItem(int index) {
		return inputValueArray.get(index);
	}
	public int getoutputValueArrayItem(int index) {
		return outValueArray.get(index);
	}
	public void ClearGateVector() {
		outputCount = 0;
		inputValueArray.clear();
		outValueArray.clear();
	}
}
