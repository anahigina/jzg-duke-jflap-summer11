package automata.fsa;

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
	



}
