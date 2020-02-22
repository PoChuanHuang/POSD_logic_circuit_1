package com.shape;

public class CircuitMap {
	public int gate;      /** 第幾個gate*/
	public int gateType;  /** 1: AND, 2: OR, 3: NOT*/
	public int inputType; /** 0:input 1:output*/
	public int iPinST;    /** 第幾個iPin inputType=0的時候用*/
	public int gateST;    /** 第幾個gate inputType=1的時候用*/
	public GateVector gateVector; /**用來放該gate的狀態 */
	
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
