package automata.mealy;

import JFLAPnew.formaldef.symbols.SymbolString;
import automata.fsa.FSATransitionLabel;

public class MooreTransitionLabel extends FSATransitionLabel {

	public MooreTransitionLabel() {
		super();
	}

	public MooreTransitionLabel(SymbolString subList) {
		super(subList);
	}

}
