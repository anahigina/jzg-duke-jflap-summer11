package automata.mealy;

import java.util.HashSet;
import java.util.Set;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import automata.ITransitionLabel;
import automata.TransitionLabel;

public class MealyTransitionLabel extends TransitionLabel {

	public MealyTransitionLabel(SymbolString read, SymbolString output) {
		super(read, output);
	}

	public void setOutput(SymbolString output) {
		this.setByIndex(MealyTransition.OUTPUT, output);
	}

	public void setInput(SymbolString read) {
		this.setByIndex(MealyTransition.INPUT, read);
	}

	public SymbolString getOutput() {
		return getByIndex(MealyTransition.OUTPUT);
	}


	public SymbolString getInput() {
		return getByIndex(MealyTransition.INPUT);
	}

	@Override
	public Set<? extends Symbol> getUniqueSymbols() {
		HashSet<Symbol> set = new HashSet<Symbol>(this.getOutput());
		set.addAll(this.getInput());
		return set;
	}

}
