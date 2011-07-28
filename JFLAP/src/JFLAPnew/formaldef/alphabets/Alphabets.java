package JFLAPnew.formaldef.alphabets;

import gui.errors.BooleanWrapper;

import java.util.ArrayList;
import java.util.List;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.variable.Variable;

public class Alphabets {


	public static <T extends Symbol> List<T> getSpecialSymbols(IAlphabet<T> alph){
		ArrayList<T> specials = new ArrayList<T>();
		for (T s: alph.getSymbols())
			if (s.isSpecial())
				specials.add(s);
		return specials;
	}
	
	
	public static boolean clearSpecialSymbols(IAlphabet<?> alph){
		boolean changed = false;
		for (Symbol s: alph.getSymbols()){
			if (s.isSpecial()) changed = true;
			s.setSpecial(false);
		}
		return changed;
	}


	public static <T extends Symbol> BooleanWrapper setSpecialSymbol(
			IAlphabet<T> alph, T symbol) {
		Boolean contains = true;
//				alph.contains(symbol);
//		if (contains) Alphabets.clearSpecialSymbols(alph);
		symbol.setSpecial(contains);
		return new BooleanWrapper(contains, "The " + alph.getName() + 
				" does not contain the symbol " + symbol + ".");
	}
	
	
}
