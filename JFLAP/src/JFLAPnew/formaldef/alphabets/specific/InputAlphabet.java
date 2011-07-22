package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class InputAlphabet extends Alphabet<Terminal>{

	public InputAlphabet() {
		super();
	}

	public InputAlphabet(FormalDefinition parent) {
		super(parent);
	}

	@Override
	public BooleanWrapper canAdd(Terminal sym) {
		TapeAlphabet t = this.getParent().getAlphabetByClass(TapeAlphabet.class);
		if (t != null){
			return  BooleanWrapper.combineWrappers(
				 		new BooleanWrapper(t.contains(sym), "This symbol is not a member of the " +
				 							t.getName() + " Therefore, since " + this.getName() + " is a subset of the " +
											t.getName() + " this symbol cannot be added."),
						super.canAdd(sym));
		}
		return super.canAdd(sym);
	}

	@Override
	public Integer getPriority() {
		return 4;
	}

	@Override
	public String getName() {
		return "Input Alphabet";
	}

	@Override
	public char[] getDisallowedCharacers() {
		return new char[0];
	}

}
