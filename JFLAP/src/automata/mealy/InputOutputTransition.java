package automata.mealy;

import JFLAPnew.formaldef.symbols.SymbolString;
import automata.ITransitionLabel;
import automata.State;
import automata.Transition;
import automata.TransitionLabel;


public abstract class InputOutputTransition<T extends ITransitionLabel> extends Transition<T> {

	protected T myLabel;
	
	public InputOutputTransition(State from, State to, T label) {
		super(from, to, label);
	}

	public abstract SymbolString getOutput();
    
    public abstract SymbolString getInput();

	@Override
	public T getLabel() {
		return myLabel;
	}

	@Override
	public void setLabel(T label) {
		myLabel = label;
	}
    
    
}
