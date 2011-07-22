package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
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
		return 5;
	}
	
	@Override
	public BooleanWrapper isComplete() {
		return BooleanWrapper.combineWrappers(super.isComplete(), 
				new BooleanWrapper(this.getBottomOfStackSymbol() != null, 
				"The " + this.getName() + " requires a bottom of stack variable."));
	}

	public Terminal getBottomOfStackSymbol() {
		return this.getSpecialSymbol();
	}
	
	public BooleanWrapper setBottomOfStackSymbol(Terminal t) {
		return this.setSpecialSymbol(t);
	}


	@Override
	public String getName() {
		return "Stack Alphabet";
	}
	
	@Override
	public char[] getDisallowedCharacers() {
		return new char[0];
	}
	
	@Override
	public Terminal getSpecialSymbol() {
		for (Terminal s: this.getSymbols())
			if (s.isSpecial())
				return s;
		return null;
	}
	
	@Override
	public BooleanWrapper setSpecialSymbol(Terminal t) {
		return this.setSpecialSymbol(t.getString());
	}

	@Override
	public BooleanWrapper setSpecialSymbol(String string) {
		Terminal sym = this.getSymbol(string);
		if (sym != null){
			this.clearSpecialSymbol();
			sym.setSpecial(true);
		}
		return new BooleanWrapper(sym != null, "The " + this.getName() + " does not contain a symbol " + 
							"corresponding to the input string");
	}

	@Override
	public void clearSpecialSymbol() {
		for (Terminal s: this.getSymbols())
			s.setSpecial(false);
	}

	@Override
	public String getSpecialSymbolName() {
		return "Bottom of Stack Symbol";
	}

}
