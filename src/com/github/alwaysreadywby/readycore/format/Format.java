package com.github.alwaysreadywby.readycore.format;

public interface Format<T,S> {
	public T process(S source,Object... args) throws FormatException;
}
