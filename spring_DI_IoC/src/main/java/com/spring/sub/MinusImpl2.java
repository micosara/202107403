package com.spring.sub;

public class MinusImpl2 extends MinusImpl{

	@Override
	public int minus(int a, int b) {	
		return super.minus(a, b)-10;
	}

	@Override
	public int minus(int a, int b, int c) {
		return super.minus(a, b, c)-10;
	}

}
