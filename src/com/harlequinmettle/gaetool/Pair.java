package com.harlequinmettle.gaetool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

public class Pair implements Serializable{
public float low;
public float high;

public Pair(float low,float high){
		this.low =  new BigDecimal(low).round(new MathContext(3)).floatValue();
		this.high =  new BigDecimal(high).round(new MathContext(3)).floatValue();
	}

@Override
public String toString() {
	return "L-H:("+low+")-("+high+")";
}
	
}
