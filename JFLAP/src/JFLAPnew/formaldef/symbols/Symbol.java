package JFLAPnew.formaldef.symbols;

import gui.errors.BooleanWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import JFLAPnew.formaldef.AlphabetException;
import JFLAPnew.formaldef.grouping.GroupingPair;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public abstract class Symbol implements Comparable<Symbol>, Cloneable{

	private String myString;
	private boolean amSpecial;
	
	public Symbol(String s) { 
		myString = s;
	}
	
	
	
	public String getString(){
		return myString;
	}
	
	public void setString(String s){
		myString = s;
	}


	@Override
	public int hashCode() {
		return myString.hashCode();
	}


	public boolean containsCharacters(char ... chars) {
		return SymbolHelper.containsCharacters(this, chars);
	}
	

	public int length() {
		return myString.length();
	}

	
	public String getName(){
		return this.getClass().getSimpleName();
	}
	
	@Override
	public boolean equals(Object o){
		return this.getString().equals(((Symbol) o).getString());
	}
	
	
	@Override
	public int compareTo(Symbol o) {
		if (this.isSpecial() && !o.isSpecial()){
			return -1;
		}
		if (!this.isSpecial() && o.isSpecial()){
			return 1;
		}
		return this.getString().compareTo(o.getString());
	}


	public boolean isSpecial() {
		return amSpecial;
	}


	public void setSpecial(boolean special) {
		this.amSpecial = special;
	}
	
	public String toString(){
		return this.getString();
	}

	@Override
	public Symbol clone() {
		
		try {
			Symbol s = (Symbol) this.getClass().getConstructors()[0].newInstance(this.getString());
			s.setSpecial(this.isSpecial());
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AlphabetException("Problem cloning " + this.toString());
		}
		
	}
	
	
}

