package JFLAPnew.formaldef.alphabets.specific;

import java.util.ArrayList;
import java.util.List;

import gui.errors.BooleanWrapper;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.grouping.IGrouping;
import JFLAPnew.formaldef.symbols.Symbol;

public abstract class GrammarAlphabet<T extends Symbol> extends Alphabet<T> {

	public GrammarAlphabet() {
		super();
	}

	public GrammarAlphabet(FormalDefinition parent) {
		super(parent);
	}

	protected BooleanWrapper checkNoOveralappingChars(String string, Alphabet alph) {
		for (char c: string.toCharArray()){
			Symbol s = alph.getFirstSymbolContaining(c);
			if (s != null)
				return new BooleanWrapper(false, "Without grouping, the " + this.getName() + 
						" set cannot share characters with any symbol in the " + 
						alph.getName() +", and " + string + " has \'" + c + "\' in common with " + s.toString());
		}
		return new BooleanWrapper(true, "Symbol " + string + " can be added sucessfully");
	}


	protected BooleanWrapper checkIdentical(String string, String otherAlphName) {
		return new BooleanWrapper(!this.containsSymbolWithString(string), "You may not add a symbol to " + this.getName() + 
				 " which has String identical to a symbol in the " + otherAlphName);
	}

	@Override
	public ArrayList<Character> getDisallowedCharacters() {
		ArrayList<Character> disallowed = super.getDisallowedCharacters();
		disallowed.add(new Character('$'));
		return disallowed;
	}
	

}
