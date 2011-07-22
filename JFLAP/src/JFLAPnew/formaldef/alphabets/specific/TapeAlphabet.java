package JFLAPnew.formaldef.alphabets.specific;

import gui.errors.BooleanWrapper;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class TapeAlphabet extends Alphabet<Terminal> {

	public TapeAlphabet(FormalDefinition parent) {
		super(parent);
	}


	public TapeAlphabet() {
		super();
	}


	@Override
	public BooleanWrapper remove(Terminal sym) {
		InputAlphabet input = this.getParent().getAlphabetByClass(InputAlphabet.class);
		
		if (input != null && input.canRemove(sym).isTrue()){
			return BooleanWrapper.combineWrappers(input.remove(sym), super.remove(sym));
		}
		return super.remove(sym);
	}
	
	

	@Override
	public BooleanWrapper canModify(Terminal from, String to) {
		InputAlphabet input = this.getParent().getAlphabetByClass(InputAlphabet.class);
		BooleanWrapper bw;
		if (input.contains(from)){
			 bw = super.canModify(from, to);
			 input.add(from);
		}
		else
			bw =  super.canModify(from, to);
		
		return bw;
	}


	@Override
	public BooleanWrapper modify(Terminal from, String to) {
		InputAlphabet input = this.getParent().getAlphabetByClass(InputAlphabet.class);
		
		BooleanWrapper mod;
		
		if ((mod = super.modify(from, to)).isTrue() && input.contains(from))
			mod = BooleanWrapper.combineWrappers(input.modify(from, to), mod);
		return mod;
	}


	@Override
	public Integer getPriority() {
		return 3;
	}

	@Override
	public String getName() {
		return "Tape Alphabet";
	}
	
	@Override
	public char[] getDisallowedCharacers() {
		//TODO: Add blank? anything else?
		return new char[0];
	}


}
