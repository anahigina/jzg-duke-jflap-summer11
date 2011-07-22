package JFLAPnew.formaldef.alphabets;

import gui.errors.BooleanWrapper;

import java.util.List;
import java.util.Set;

import JFLAPnew.formaldef.grouping.IGrouping;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.variable.Variable;


public interface IAlphabet<T extends Symbol> extends Comparable<IAlphabet>, 
													 IComplete, 
													 Cloneable,
													 IDefinitionChild{

	public Integer getPriority();
	
	public BooleanWrapper canAdd(T sym);
	
	public BooleanWrapper canRemove(T sym);
	
	public BooleanWrapper canModify(T from, String to);
	
	public BooleanWrapper addAll(String ... strings);
	
	public BooleanWrapper addAll(T ... symbols);
	
	public BooleanWrapper add(T sym);
	
	public BooleanWrapper add(String s);
	
	public boolean containsSymbolString(String ... strings);
	
	public boolean contains(T ... sym);
	
	public BooleanWrapper remove(T sym);
	
	public BooleanWrapper modify(T from, String to);
	
	public Set<Character> getUniqueCharacters();

	public String getName();
	
	public T getFirstSymbolContaining(char ... c);
	
	public T getSymbol(String sym);
	
	public Set<T> getSymbols();
	
	public String toString();
	
	public List<T> sortedList();
	
	public Class<T> getSymbolClass();
	
	public T createDesiredSymbol(String s);
	
	public char[] getDisallowedCharacers();
}
