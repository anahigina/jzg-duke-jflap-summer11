package JFLAPnew.formaldef;

import grammar.Grammar;
import gui.errors.BooleanWrapper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import JFLAPnew.formaldef.alphabets.Alphabet;
import JFLAPnew.formaldef.alphabets.IAlphabet;
import JFLAPnew.formaldef.alphabets.IComplete;
import JFLAPnew.formaldef.alphabets.specific.TerminalAlphabet;
import JFLAPnew.formaldef.alphabets.specific.VariableAlphabet;
import JFLAPnew.formaldef.symbols.Symbol;

public abstract class FormalDefinition extends TreeSet<IAlphabet> implements IComplete, Comparable<FormalDefinition>{

	public FormalDefinition(Class<? extends IAlphabet> ... alphabets){
		super();
		initializeAlphabets(alphabets); 
			
	}
	
	public FormalDefinition(){
		super();
		initializeAlphabets(FormalDefintionFactory.getDefinitionRequirements(this.getClass()));
	}

	private void initializeAlphabets(Class<? extends IAlphabet>... alphabets) {
		for (Class<? extends IAlphabet> alph: alphabets)
			try {
				this.add(alph.getConstructor(FormalDefinition.class).newInstance(this));
			} catch (Exception e) {
				throw new AlphabetException("Error constructing a Formal Defintion from classes");
			}
	}
	
	
	public <T extends IAlphabet> T getAlphabetByClass(Class<T> clazz) {
		for (IAlphabet alph : this){
			if (alph.getClass().isAssignableFrom(clazz))
				return (T) alph;
		}
		return null;
	}

	@Override
	public BooleanWrapper isComplete() {
		BooleanWrapper amComplete = new BooleanWrapper(true);
		for (IAlphabet alph: this){
			amComplete = alph.isComplete();
			if (amComplete.isFalse())
				return amComplete;
		}
		return amComplete;
	}

	public void addAlphabets(Alphabet ... alphabets) {
		super.addAll(Arrays.asList(alphabets));
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for (IAlphabet alph : this){
			sb.append(alph + "\n");
		}
		
		return sb.toString();
	}


	public List<IAlphabet> sortedAlphabetList() {
		List<IAlphabet> list = Arrays.asList(this.toArray(new IAlphabet[0]));
		Collections.sort(list);
		return list;
	}

	@Override
	public Object clone() {
		try {
			FormalDefinition fd = (FormalDefinition) getClass().newInstance();
			for (IAlphabet alph: this)
				fd.add(alph.clone());
			return fd;
		} catch (Exception e) {
			throw new AlphabetException("Formal Definition clone failed.");
		}
		
	}

	
	public boolean equals(FormalDefinition other){
		for (IAlphabet alph: this){
			if (!other.containsByClass(alph.getClass()) || 
					!alph.equals(other.getAlphabetByClass(alph.getClass())))
				return false;
		}
		return true;
	}

	public boolean containsByClass(Class<? extends IAlphabet> clazz) {
		for (IAlphabet alph : this){
			if (alph.getClass().isAssignableFrom(clazz))
				return true;
		}
		return false;
	}

	@Override
	public int compareTo(FormalDefinition o) {
		return this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof FormalDefinition)
			return this.compareTo((FormalDefinition) o) == 0;
		return false;
	}
	
	public abstract String getName();

	public void eraseAlphabets() {
		super.clear();
	}

}
