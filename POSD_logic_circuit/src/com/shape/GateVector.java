package com.shape;

import java.util.ArrayList;
import java.util.List;

public class GateVector {
	public int gate;            /** 第幾個gate*/
	public int gateType;        /** 1: AND, 2: OR, 3: NOT*/
	public int inputCount  =0;  /** 有幾個input*/
	public int outputCount =0 ; /** 有幾個output*/
	public List<Integer> inputValueArray = new ArrayList<>(); /** 此gate的input值 */
	public List<Integer> outValueArray   = new ArrayList<>(); /** 此gate的output值 */
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
