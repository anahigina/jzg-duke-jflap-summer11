package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class TerminalAlphabet extends GrammarAlphabet<Terminal> {

	public TerminalAlphabet(FormalDefinition parent) {
		super(parent);
	}

	@Override
	public BooleanWrapper canAdd(Terminal sym) {
		BooleanWrapper wrapper = new BooleanWrapper(true);
		VariableAlphabet vars = this.getParent().getAlphabetByClass(VariableAlphabet.class);
		if (vars != null){
			if (vars.usingGrouping()){
				wrapper = vars.checkIdentical(
						vars.getGrouping().getOpenGroup() + sym.getString() + vars.getGrouping().getCloseGroup(), 
						this.getName());
			}
			else {
				wrapper = this.checkNoOveralappingChars(sym.getString(), vars);
			}
		}
		
		return BooleanWrapper.combineWrappers(wrapper, super.canAdd(sym));
	}

	@Override
	public Integer getPriority() {
		return 2;
	}

	@Override
	public String getName() {
		return "Terminal Alphabet";
	}

	@Override
	public char[] getDisallowedCharacers() {
		VariableAlphabet vars = this.getParent().getAlphabetByClass(VariableAlphabet.class);
		if (vars.usingGrouping()){
			return new char[]{vars.getGrouping().getOpenGroup().charAt(0), 
								vars.getGrouping().getCloseGroup().charAt(0)};
		}
		return new char[0];
	}
	
}
