package com.github.alwaysreadywby.readycore.format;

import java.util.Vector;

public class SimpleStringFormat extends StringFormat{
	
	public static final int MAX_PROCESS=80;
	
	private Vector<StringFormat> lPlaceHolders;
	private int nLevel;
	
	public SimpleStringFormat() {
		lPlaceHolders=new Vector<StringFormat>();
		nLevel=0;
	}
	
	public void addPlaceHolder(String source,String target) {
		lPlaceHolders.add(new StringReplacer("%"+source+"%",target));
	}
	
	public void addPlaceHolder(StringFormat format) {
		lPlaceHolders.add(format);
	}
	
	public void clearPlaceHolder() {
		lPlaceHolders.clear();
	}

	@Override
	public String process(String source, Object... args) throws FormatException {
		//Stop processing and throws exception when max process time is exceeded
		if(++nLevel>MAX_PROCESS) {
			nLevel=0;
			throw(new CircularReferenceException(source));
		}
		String result=new String(source);
		for(StringFormat f:lPlaceHolders) {
			result=f.process(result, args);
		}
		for(int i=0;i<args.length;i++) {
			result=result.replaceAll("%arg"+i+"%", args[i].toString());
		}
		if(!result.equals(source)) {
			result=process(result,args);
		}else {
			result=result.replaceAll("&", "§");
			result=result.replaceAll("§§","&");
		}
		nLevel--;
		return result;
	}

}
