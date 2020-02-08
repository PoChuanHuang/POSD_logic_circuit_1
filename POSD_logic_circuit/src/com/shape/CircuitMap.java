package com.shape;

public class CircuitMap {
	public int gateType; /** 1: AND, 2: OR, 3: NOT*/
	public int inputType;/** 0:input 1:output*/
	public int input;    /** ²Ä´X­ÓiPin*/
	
	public CircuitMap(int gateType , int inputType , int input){
		super();
		this.gateType  = gateType;
		this.inputType = inputType;
		this.input     = input;
	}
}
