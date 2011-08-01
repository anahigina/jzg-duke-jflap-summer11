package JFLAPnew.formaldef.alphabets.specific;

import java.util.ArrayList;
import java.util.List;

import gui.errors.BooleanWrapper;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.ISpecialSymbol;
import JFLAPnew.formaldef.gui.GUIConstants;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.terminal.Terminal;

public class TapeAlphabet extends Alphabet<Terminal> {

	private char BLANK;

	public TapeAlphabet(){
		this(null);
	}
	
	public TapeAlphabet(FormalDefinition parent) {
		super(parent);
		BLANK =  '\u25A1';
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
		BooleanWrapper canModify = super.remove(from);
		if (canModify.isTrue()) {
			canModify = this.canAdd(this.createDesiredSymbol(to));
			this.add(from);
		}
		
		return canModify;
	}
	


	@Override
	public Integer getPriority() {
		return GUIConstants.TAPE_PRIORITY;
	}

	@Override
	public String getName() {
		return "Tape Alphabet";
	}
	
	@Override
	public ArrayList<Character> getDisallowedCharacters() {
		ArrayList<Character> disallowed = super.getDisallowedCharacters();
		disallowed.add(this.getBlankSymbol());
		return disallowed;
	}


	public void setBlankSymbol(char c){
		BLANK = c;
	}
	
	private char getBlankSymbol() {
		return BLANK;
	}



}
