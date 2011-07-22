package JFLAPnew.formaldef.symbols;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.symbols.variable.Variable;

public class SymbolString extends LinkedList<Symbol> {

	public SymbolString(String in, IAlphabet ... alphabets){
		super();
		this.addAll(SymbolString.createFromString(in, alphabets));
	}

	public SymbolString() {
		super();
	}

	public String toString(){
		String string = "";
		for (Symbol s: this){
			string += s.toString();
		}
		return string;
	}
	
	private static LinkedList<? extends Symbol> createFromString(String in,
			IAlphabet ... alphabets) {
		
		String temp = "";
		SymbolString symbols = new SymbolString();
		
		for (int i = 0; i < in.length(); i++){
			temp += in.charAt(i);
			for (IAlphabet alph: alphabets){
				if (alph.containsSymbolString(temp)){
					symbols.add(alph.createDesiredSymbol(temp));
					temp = "";
					break;
				}
			}
		}
		
		return symbols;
	}

	public <T extends Symbol> Set<T> getSymbolsOfClass(Class<T> clazz) {
		Set<T> results = new TreeSet<T>();
		for (Symbol s: this){
			if (s.getClass().isAssignableFrom(clazz))
				results.add((T) s);
		}
		
		return results;
	}
	
	public boolean equals(SymbolString o){
		Iterator<Symbol> me = this.iterator(),
				 		 other = o.iterator();
		while(me.hasNext() && other.hasNext()){
			Symbol sMe = me.next(),
				   sOther = other.next();
			
			if(!sMe.equals(sOther) || !sMe.getClass().equals(sOther.getClass()))
					return false;
		}
		
		if (me.hasNext() || other.hasNext())
			return false;
		
		return true;
		
	}
	
}
