package automata;

import java.util.Collection;
import java.util.Set;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;

public interface ITransitionLabel extends Cloneable{

	public abstract int hashCode();

	public abstract boolean equals(Object o);

	public abstract void removeSymbol(Symbol s);

	public abstract SymbolString getByIndex(int column);

	public abstract void setByIndex(int i, SymbolString s);

	public abstract String toString();

	public abstract Set<? extends Symbol> getUniqueSymbols();

}