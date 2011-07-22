package JFLAPnew.formaldef.alphabets.specific;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class OutputAlphabet extends Alphabet<Terminal> {

	public OutputAlphabet() {
		super();
	}

	public OutputAlphabet(FormalDefinition parent) {
		super(parent);
	}

	@Override
	public Integer getPriority() {
		return 6;
	}

	@Override
	public String getName() {
		return "Output Alphabet";
	}

	@Override
	public char[] getDisallowedCharacers() {
		return new char[0];
	}
	
}
