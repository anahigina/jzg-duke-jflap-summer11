package automata.pda;

import java.util.HashSet;
import java.util.Set;

import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import automata.TransitionLabel;

public class PDATransitionLabel extends TransitionLabel {

	
	public PDATransitionLabel(SymbolString in, SymbolString pop,
			SymbolString push) {
		super(in, pop, push);
	}

	public void setPush(SymbolString push) {
		this.setByIndex(PDATransition.PUSH, push);
	}

	public void setPop(SymbolString pop) {
		this.setByIndex(PDATransition.POP, pop);
	}

	public void setInput(SymbolString in) {
		this.setByIndex(PDATransition.INPUT, in);
	}


	@Override
	public String toString() {
		return getInputToRead() + " , " + getPop() + " ; " + getPush();
	}

	public SymbolString getPush() {
		return this.getByIndex(PDATransition.PUSH);
	}

	public SymbolString getPop() {
		return this.getByIndex(PDATransition.POP);
	}

	public SymbolString getInputToRead() {
		return this.getByIndex(PDATransition.INPUT);
	}

	@Override
	public Set<? extends Symbol> getUniqueSymbols() {
		HashSet<Symbol> set = new HashSet<Symbol>(this.getPop());
		set.addAll(this.getPush());
		set.addAll(this.getInputToRead());
		return set;
	}

}
