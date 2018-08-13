package com.github.alwaysreadywby.readycore.format;

public abstract class StringFormat implements Format<String, String> {

	@Override
	public abstract String process(String source, Object... args) throws FormatException;

}
