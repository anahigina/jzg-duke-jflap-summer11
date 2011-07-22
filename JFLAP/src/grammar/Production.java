/*
 *  JFLAP - Formal Languages and Automata Package
 * 
 * 
 *  Susan H. Rodger
 *  Computer Science Department
 *  Duke University
 *  August 27, 2009

 *  Copyright (c) 2002-2009
 *  All rights reserved.

 *  JFLAP is open source software. Please see the LICENSE for terms.
 *
 */





package grammar;

import gui.environment.Universe;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Set;

import JFLAPnew.formaldef.AlphabetException;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;

/**
 * A Production object is a simple abstract class that represents a production
 * rule in a grammar.
 * 
 * @author Ryan Cavalcante
 */

public class Production implements Serializable, Cloneable {
	/**
	 * Creates an instance of <CODE>Production</CODE>.
	 * 
	 * @param lhs
	 *            the left hand side of the production rule.
	 * @param rhs
	 *            the right hand side of the production rule.
	 */
	public Production(SymbolString lhs, SymbolString rhs) {
		myLHS = lhs;
		myRHS = rhs;
	}

	/**
	 * Sets the right hand side of production to <CODE>rhs</CODE>.
	 * 
	 * @param rhs
	 *            the right hand side
	 */
	public void setRHS(SymbolString rhs) {
		myRHS = rhs;
	}

	/**
	 * Sets the left hand side of production to <CODE>lhs</CODE>.
	 * 
	 * @param lhs
	 *            the left hand side
	 */
	public void setLHS(SymbolString lhs) {
		myLHS = lhs;
	}

	/**
	 * Returns a string representation of the left hand side of the production
	 * rule.
	 * 
	 * @return a string representation of the lhs.
	 */
	public SymbolString getLHS() {
		return myLHS;
	}

	/**
	 * Returns a string representation of the right hand side of the production
	 * rule.
	 * 
	 * @return a string representation of the rhs.
	 */
	public SymbolString getRHS() {
		return myRHS;
	}

	/**
	 * Returns all symbols (both variables in terminals) in a production.
	 * 
	 * @return all symbols in a production
	 */
	public String[] getSymbols() {
		SortedSet symbols = new TreeSet();
		symbols.addAll(Arrays.asList(getVariables()));
		symbols.addAll(Arrays.asList(getTerminals()));
		return (String[]) symbols.toArray(new String[0]);
	}

	/**
	 * Returns all variables in the production.
	 * 
	 * @return all variables in the production.
	 */
	public Set<Variable> getVariables() {
		TreeSet<Variable> results = new TreeSet<Variable>(this.getVariablesOnLHS());
		results.addAll(this.getVariablesOnRHS());
		return results;
	}

	/**
	 * Returns all variables on the left hand side of the production.
	 * 
	 * @return all variables on the left hand side of the production.
	 */
	public Set<Variable> getVariablesOnLHS() {
		return myLHS.getSymbolsOfClass(Variable.class);
	}

	/**
	 * Returns all variables on the right hand side of the production.
	 * 
	 * @return all variables on the right hand side of the production.
	 */
	public Set<Variable> getVariablesOnRHS() {
		return myRHS.getSymbolsOfClass(Variable.class);
	}

	/**
	 * Returns all terminals in the production.
	 * 
	 * @return all terminals in the production.
	 */
	public Set<Terminal> getTerminals() {
		TreeSet<Terminal> results = new TreeSet<Terminal>(this.getTerminalsOnLHS());
		results.addAll(this.getTerminalsOnRHS());
		return results;
	}

	/**
	 * Returns all terminals on the right hand side of the production.
	 * 
	 * @return all terminals on the right hand side of the production.
	 */
	public Set<Terminal> getTerminalsOnRHS() {
		return myRHS.getSymbolsOfClass(Terminal.class);
	}

	/**
	 * Returns true if <CODE>production</CODE> is equivalent to this
	 * production (i.e. they have identical left and right hand sides).
	 * 
	 * @param production
	 *            the production being compared to this production
	 * @return true if <CODE>production</CODE> is equivalent to this
	 *         production (i.e. they have identical left and right hand sides).
	 */
	public boolean equals(Object production) {
		if (production instanceof Production) {
			Production p = (Production) production;
			return getRHS().equals(p.getRHS()) && getLHS().equals(p.getLHS());
		}
		return false;
	}

	/**
	 * Returns a hashcode for this production.
	 * 
	 * @return the hashcode for this production
	 */
	public int hashCode() {
		return myRHS.hashCode() ^ myLHS.hashCode();
	}

	/**
	 * Returns all terminals on the left hand side of the production.
	 * 
	 * @return all terminals on the left hand side of the production.
	 */
	public Set<Terminal> getTerminalsOnLHS() {
		return myLHS.getSymbolsOfClass(Terminal.class);
	}

	/**
	 * Returns a string representation of the production object.
	 * 
	 * @return a string representation of the production object.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(getLHS());
		// buffer.append("->");
		buffer.append('\u2192');
		buffer.append(getRHS().size() == 0 ? Universe.curProfile.getEmptyString() : getRHS());
		// buffer.append('\n');
		return buffer.toString();
	}
	

	@Override
	protected Production clone() {
		try {
			return this.getClass().
							getConstructor(SymbolString.class, SymbolString.class).
								newInstance(myLHS.clone(), myRHS.clone());
		} catch (Exception e) {
			throw new AlphabetException("Error cloneing Production");
		}
	}

	/**
	 * Deprecated - this now simply does the same thing as getRHS, returning
	 * the symbol string corresponding to the Right hand Side of this production
	 * Returns the sequence of symbols in either the left or right hand side.
	 * For example, for the production <CODE>A -> BCD</CODE> this would return
	 * the array of strings <CODE>{"B","C","D"}</CODE>.
	 */
	@Deprecated
	public SymbolString getSymbolsOnRHS() {
		return myRHS;
	}

	/** the left hand side of the production. */
	protected SymbolString myLHS;

	/** the right hand side of the production. */
	protected SymbolString myRHS;
}
