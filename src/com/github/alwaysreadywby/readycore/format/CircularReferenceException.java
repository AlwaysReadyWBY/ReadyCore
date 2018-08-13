package com.github.alwaysreadywby.readycore.format;

public class CircularReferenceException extends FormatException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8687894811384139805L;
	
	private String sResult;
	
	public CircularReferenceException(String result){
		sResult=result;
	}
	
	@Override
	public String getResult() {
		return sResult;
	}

}
