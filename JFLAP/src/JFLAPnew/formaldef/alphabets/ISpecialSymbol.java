package JFLAPnew.formaldef.alphabets;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.symbols.Symbol;

public interface ISpecialSymbol<T extends Symbol> {

	public abstract T getSpecialSymbol();

	public abstract BooleanWrapper setSpecialSymbol(T s);

	public abstract BooleanWrapper setSpecialSymbol(String string);
	
	public abstract void clearSpecialSymbol();
	
	public abstract String getSpecialSymbolName();

}