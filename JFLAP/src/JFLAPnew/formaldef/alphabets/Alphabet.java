package JFLAPnew.formaldef.alphabets;

import gui.errors.BooleanWrapper;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import JFLAPnew.formaldef.AlphabetException;
import JFLAPnew.formaldef.FormalDefinition;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolHelper;
import JFLAPnew.formaldef.symbols.variable.Variable;

public abstract class Alphabet<T extends Symbol> implements IAlphabet<T> {


	private TreeSet<T> mySymbols;
	private FormalDefinition myParent;
	
	public Alphabet(FormalDefinition parent){
		this();
		setParent(parent);
	}
	
	public Alphabet() {
		mySymbols = new TreeSet<T>();
	}

	@Override
	public Set<T> getSymbols() {
		return mySymbols;
	}

	@Override
	public String toString() {
		return this.getName() + ": " + mySymbols.toString();
	}

	@Override
	public int compareTo(IAlphabet o) {
		return this.getPriority().compareTo(o.getPriority());
	}
	
	public boolean equals(IAlphabet o){
		return this.getPriority().equals(o.getPriority());
	}

	@Override
	public BooleanWrapper isComplete() {
		return new BooleanWrapper(!mySymbols.isEmpty(), "The " + this.toString() + 
				" is incomplete because it is empty.");
	}

	@Override
	public List<T> sortedList() {
		List<Symbol> list = Arrays.asList(mySymbols.toArray(new Symbol[0]));
		Collections.sort(list);
		List<T> out = new ArrayList<T>();
		for (Symbol s: list)
			out.add((T) s);
		return out;
	}


	@Override
	public BooleanWrapper addAll(String... strings) {
		T[] symbols = (T[]) Array.newInstance(this.getSymbolClass(), strings.length);
		for (int i = 0; i < strings.length; i++)
			symbols[i] = this.createDesiredSymbol(strings[i]);
		return this.addAll(symbols);

	}

	@Override
	public BooleanWrapper addAll(T... symbols) {
		List<BooleanWrapper> bools = new ArrayList<BooleanWrapper>();
		for (T s: symbols){
			bools.add(this.add(s));
		}
		return BooleanWrapper.combineWrappers(true, bools.toArray(new BooleanWrapper[0]));
	}

	@Override
	public BooleanWrapper add(T sym) {
		BooleanWrapper canAdd = this.canAdd(sym);
		if (canAdd.isTrue()) mySymbols.add(sym);
		return canAdd;
	}

	@Override
	public BooleanWrapper add(String s) {
		return this.add(this.createDesiredSymbol(s));
	}

	@Override
	public boolean containsSymbolString(String... strings) {
		for	(String s: strings){
			if (!this.contains(this.createDesiredSymbol(s))) 
				return false; 
		}
		return true;
	}

	@Override
	public boolean contains(T... symbols) {
		for	(T s: symbols){
			if (!mySymbols.contains(s))
					return false; 
		}
		return true;
	}


	@Override
	public BooleanWrapper canAdd(T sym) {
		if (sym.length() <= 0)
			return new BooleanWrapper(false, "You may not add a symbol of no length.");
		for (char c: this.getDisallowedCharacers()){
			if (sym.containsCharacters(c))
				return new BooleanWrapper(false, "The character " + c + " is disallowed for this " + this.getName() +
						". For more information on allowability rules, visit <LINKLINK>.");
		}
		for(T s: mySymbols){
			if (areTooSimilar(sym, s))
				return new BooleanWrapper(false, "The " + sym.getString() + " is not allowed because " +
						"it is too similar to the - " + s.getString() + " -  in the " + this.getName() + ". "+ 
						"For more information on allowability rules, visit <LINKLINK>.");
		}
		return new BooleanWrapper(true, "Symbol " + sym.getString() + " can be added to the " + this.getName() +" sucessfully");
	}

	protected boolean areTooSimilar(T s1, T s2) {
		return SymbolHelper.containsSimilarString(s2, s1) || 
				SymbolHelper.containsSimilarString(s1, s2);
	}

	@Override
	public BooleanWrapper canRemove(T sym) {
		return BooleanWrapper.combineWrappers(new BooleanWrapper(!mySymbols.isEmpty(), 
							"The " + this.toString() +" is empty, you may not remove symbols from it"),
											  new BooleanWrapper(this.contains(sym), "This " + this.getName() + " does not contain " +
							"the symbol " + sym.getString()));
	
	}

	//TODO: This seems silly...is there a better way to check for "can change" than basically 
	//changing without CHANGING the symbol?
	@Override
	public BooleanWrapper canModify(T from, String to) {
		BooleanWrapper canModify = this.remove(from);
		if (canModify.isTrue()) {
			canModify = this.canAdd(this.createDesiredSymbol(to));
			this.add(from);
		}
		
		return canModify;
	}

	@Override
	public T getSymbol(String sym) {
		for (T s: mySymbols){
			if (sym.equals(s.getString()))
				return s;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getSymbolClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public BooleanWrapper remove(T sym) {
		BooleanWrapper canRemove = this.canRemove(sym);
		if (canRemove.isTrue()) mySymbols.remove(sym);
		return canRemove;
	}


	@Override
	public BooleanWrapper modify(T from, String to) {
		BooleanWrapper canChange = this.canModify(from, to);
		if (canChange.isTrue()) this.getSymbol(from.getString()).setString(to);
		return canChange;
	}

	@Override
	public Set<Character> getUniqueCharacters() {
		Set<Character> chars = new TreeSet<Character>();
		for (Symbol s: mySymbols){
			for (char c : s.getString().toCharArray()){
				chars.add(c);
			}
		}
		return chars;
	}

	@Override
	public T getFirstSymbolContaining(char ... chars) {
		for (T s: mySymbols){
			if (s.containsCharacters(chars))
				return s;
		}
		return null;
	}
	


	@Override
	public T createDesiredSymbol(String s) {
		try {
			return (T) this.getSymbolClass().getConstructors()[0].newInstance(s);
		} catch (Exception e) {
			throw new AlphabetException("Error with reflection in creating a desired " +
										"symbol for alphabet " + this.toString());
		}
	}

	@Override
	public FormalDefinition getParent() {
		if (myParent == null)
			throw new AlphabetException("An alphabet without a parent is a very sad alphabet indeed.");
		return myParent;
	}

	@Override
	public void setParent(FormalDefinition parent) {
		this.myParent = parent;
	}

	
	
	@Override
	protected Alphabet<T> clone() {
		
		try {
			Alphabet<T> alph = this.getClass().newInstance();
			alph.addAll(mySymbols.toArray((T[]) Array.newInstance(alph.getSymbolClass(), 0)));
			return alph;
		} catch (Exception e) {
			throw new AlphabetException("Error cloning the alphabet");
		}
		
	}

	
	

}
