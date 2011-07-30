package JFLAPnew.formaldef.alphabets.specific;

import javax.swing.JOptionPane;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.gui.definitionpanel.GUIConstants;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class InputAlphabet extends Alphabet<Terminal>{

	public InputAlphabet() {
		super();
	}

	public InputAlphabet(FormalDefinition parent) {
		super(parent);
	}

	@Override
	public BooleanWrapper add(Terminal sym) {
		TapeAlphabet t = this.getParentAlphabetOfClass(TapeAlphabet.class);
		if (t != null){
			if (t.contains(sym)){
				return super.add(t.getSymbol(sym.getString()));
			}
			else{
			BooleanWrapper canAdd = t.add(sym);
			if (canAdd.isTrue()) canAdd = super.add(sym);
			return canAdd;
			}
		}
		return super.add(sym);
	}
	
	@Override
	public BooleanWrapper modify(Terminal sym, String change) {
		TapeAlphabet t = this.getParentAlphabetOfClass(TapeAlphabet.class);
		if (t != null){
			return t.modify(sym, change);
		}
		return super.modify(sym, change);
	}

	@Override
	public Integer getPriority() {
		return GUIConstants.INPUT_PRIORITY;
	}

	@Override
	public String getName() {
		return "Input Alphabet";
	}

}
