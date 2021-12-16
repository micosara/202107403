package com.jsp.exception;

public class NotNumberException extends NumberFormatException {

	public NotNumberException() {
		super("올바른 숫자 형식이 아닙니다.");
	}
}



