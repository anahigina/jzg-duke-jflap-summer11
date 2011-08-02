package gui.grammar;

import grammar.Grammar;
import grammar.Production;
import gui.errors.BooleanWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import JFLAPnew.formaldef.symbols.SymbolString;


public class GrammarDataHelper extends ArrayList<Object[]> {

	private Grammar myGrammar;
	private LinkedList<BooleanWrapper> myWrappers;
	
	public GrammarDataHelper(Grammar g){
		myGrammar = g;
		myWrappers = new LinkedList<BooleanWrapper>();
	}
	
	
	
	
	@Override
	public void add(int index, Object[] input) {
		checkAndAddError(myGrammar.addProduction(index, this.objectToProduction(input)));
	}

	@Override
	public Object[] set(int index, Object[] input) {
		Object[] removed = this.remove(index);
		checkAndAddError(myGrammar.addProduction(index, this.objectToProduction(input)));
		return removed;
	}
	
	@Override
	public boolean add(Object[] input) {
		return !checkAndAddError(myGrammar.addProduction(this.objectToProduction(input)));
	}

	@Override
	public void clear() {
		myGrammar.clearProductions();
	}


	@Override
	public Object[] get(int index) {
		return this.ProductionToObject(myGrammar.getProductions()[index]);
	}

	@Override
	public Object[] remove(int index) {
		return this.ProductionToObject(myGrammar.removeProductionAtIndex(index));
	}

	@Override
	public int size() {
		return myGrammar.getProductions().length;
	}

	@Override
	public Iterator<Object[]> iterator() {
		ArrayList<Object[]> converted = new ArrayList<Object[]>();
		for (Production p: myGrammar.getProductions())
			converted.add(ProductionToObject(p));
		return converted.iterator();
	}
	
	
	private Production objectToProduction(Object[] input){
		SymbolString LHS = SymbolString.createFromString((String) input[0], myGrammar),
				     RHS = SymbolString.createFromString((String) input[2], myGrammar);
		if(LHS.toString().length() != ((String) input[0]).length())
			checkAndAddError(new BooleanWrapper(false, 
					"The LHS of this production has a bad character at index " + LHS.toString().length() + "."));
		if(RHS.toString().length() != ((String) input[2]).length())
			checkAndAddError(new BooleanWrapper(false, 
					"The RHS of this production has a bad character at index " + RHS.toString().length() + "."));
		return new Production(LHS, RHS);
	}
	
	private Object[] ProductionToObject(Production input){
		return new Object[]{
				input.getLHS().toString(),
				GrammarTableModel.ARROW,
				input.getRHS().toString()};
	}
	
	public Grammar getAssociatedGrammar(){
		return myGrammar;
	}
	
	/**
	 * checks if the booleanwrapper is actually an error and adds it to the 
	 * error cache if so. Returns true if the error was added to the cache.
	 * @param error
	 * @return
	 */
	public boolean checkAndAddError(BooleanWrapper error){
		if (error.isFalse())
			myWrappers.add(error);
		return error.isFalse();
	}
	
	public boolean hasErrors(){
		for (BooleanWrapper bw : myWrappers){
			if (bw.isFalse())
				return true;
		}
		return false;
	}
	
	public String getAndClearErrors(){
		String message = "The following issues occured in the most recently added production:\n" +
				BooleanWrapper.createErrorLog(myWrappers.toArray(new BooleanWrapper[0]));
		myWrappers.clear();
		return message;
	}

}
