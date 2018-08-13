package com.github.alwaysreadywby.readycore.format;

public class StringReplacer extends StringFormat {
	
	private String sSource;
	private String sTarget;
	
	public StringReplacer(String source, String target) {
		sSource=source;
		sTarget=target;
	}

	@Override
	public String process(String source, Object... args) {
		// TODO Auto-generated method stub
		return source.replaceAll(sSource, sTarget);
	}

}
