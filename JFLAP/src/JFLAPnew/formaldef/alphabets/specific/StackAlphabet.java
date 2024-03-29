package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class StackAlphabet extends Alphabet<Terminal> implements ISpecialSymbol<Terminal>{

	public StackAlphabet() {
		super();
	}

	public StackAlphabet(FormalDefinition parent) {
		super(parent);
	}

	@Override
	public Integer getPriority() {
		return GUIConstants.STACK_PRIORITY;
	}
	
	@Override
	public BooleanWrapper isComplete() {
		return BooleanWrapper.combineWrappers(super.isComplete(), 
				new BooleanWrapper(this.getBottomOfStackSymbol() != null, 
				"The " + this.getName() + " requires a bottom of stack variable."));
	}

	public Terminal getBottomOfStackSymbol() {
		return Alphabet.getFirstSpecialSymbol(this);
	}
	
	public BooleanWrapper setBottomOfStackSymbol(Terminal t) {
		return Alphabet.setSpecialSymbol(this, t);
	}


	@Override
	public String getName() {
		return "Stack Alphabet";
	}
	
	@Override
	public String getSpecialSymbolName() {
		return "Bottom of Stack Symbol";
	}

}
