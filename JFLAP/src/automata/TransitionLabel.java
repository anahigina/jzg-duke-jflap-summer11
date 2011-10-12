package automata;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import automata.mealy.MealyTransitionLabel;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;

public abstract class TransitionLabel implements Cloneable, ITransitionLabel{

	private SymbolString[] myLabelComponents;

	public TransitionLabel(SymbolString ... strings){
		myLabelComponents = strings;
	}
	
	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#hashCode()
	 */
	@Override
	public int hashCode(){
		int hash = 0;
		for (SymbolString s: this.getArray()){
			hash = hash == 0 ? s.hashCode() : hash ^ s.hashCode();
		}
		return hash;
	}

	
	
	@Override
	public TransitionLabel clone() {
		try {
			return this.getClass().cast(this.getClass().getConstructors()[0].newInstance(Arrays.copyOf(myLabelComponents, myLabelComponents.length)));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error Cloning Transition Label");
		} 
	}

	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o){
		if (!this.getClass().isAssignableFrom(o.getClass()))
			return false;
		boolean equals = true;
		SymbolString[] ar1 = this.getArray(),
					   ar2 = ((TransitionLabel) o).getArray();
		for(int i = 0; i < this.getArray().length; i++)
			equals &= ar1[i] == ar2[i];
		return equals;
	}

	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#removeSymbol(JFLAPnew.formaldef.symbols.Symbol)
	 */
	@Override
	public void removeSymbol(Symbol s){
		for (SymbolString string: this.getArray()){
			string.purgeOf(s);
		}
	}
	
	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#getByIndex(int)
	 */
	@Override
	public SymbolString getByIndex(int column) {
		return this.getArray()[column];
	}

	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#setByIndex(int, JFLAPnew.formaldef.symbols.SymbolString)
	 */
	@Override
	public void setByIndex(int i, SymbolString s){
		myLabelComponents[i] = s;
	}
	
	/* (non-Javadoc)
	 * @see automata.ITransitionLabel#toString()
	 */
	@Override
	public String toString(){
		String s = "";
		for (SymbolString string: this.getArray()){
			s += string + " | ";
		}
		
		return s.substring(0, s.length()-3);
	}
	
	
	protected SymbolString[] getArray(){
		return myLabelComponents;
	}
	
	
	
}
