package JFLAPnew.formaldef.alphabets.specific;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
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
		return GUIConstants.OUTPUT_PRIORITY;
	}

	@Override
	public String getName() {
		return "Output Alphabet";
	}

}
