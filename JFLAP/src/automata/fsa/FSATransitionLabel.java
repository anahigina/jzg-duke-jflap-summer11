package automata.fsa;

import java.util.HashSet;
import java.util.Set;

import gui.environment.Profile;
import gui.environment.Universe;
import JFLAPnew.formaldef.symbols.ISymbol;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import automata.ITransitionLabel;

public class FSATransitionLabel extends SymbolString implements ITransitionLabel{

	public FSATransitionLabel(SymbolString subList) {
		super(subList);
	}

	public FSATransitionLabel() {
	}

	@Override
	public void removeSymbol(Symbol s) {
		super.purgeOf(s);
	}

	@Override
	public SymbolString getByIndex(int column) {
		return this;
	}

	@Override
	public void setByIndex(int i, SymbolString s) {
		this.clear();
		this.addAll(s);
	}

	@Override
	public Set<? extends Symbol> getUniqueSymbols() {
		HashSet<Symbol> set = new HashSet<Symbol>(this);
		return set;
	}





}
