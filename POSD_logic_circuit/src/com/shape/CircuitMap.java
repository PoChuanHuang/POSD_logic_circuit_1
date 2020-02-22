package com.shape;

public class CircuitMap {
	public int gate;      /** �ĴX��gate*/
	public int gateType;  /** 1: AND, 2: OR, 3: NOT*/
	public int inputType; /** 0:input 1:output*/
	public int iPinST;    /** �ĴX��iPin inputType=0���ɭԥ�*/
	public int gateST;    /** �ĴX��gate inputType=1���ɭԥ�*/
	public GateVector gateVector; /**�Ψө��gate�����A */
	
	public CircuitMap(){
		super();
	}
	public CircuitMap(int gate, int gateType, int inputType, int iPinST, int gateST, GateVector gateVector){
		super();
		this.gate       = gate;
		this.gateType   = gateType;
		this.inputType  = inputType;
		this.iPinST     = iPinST;
		this.gateST     = gateST;
		this.gateVector = gateVector;
	}
}
