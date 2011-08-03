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





package automata.fsa;

import gui.environment.Universe;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import automata.Transition;
import automata.State;

/**
 * An <CODE>FSATransition</CODE> is a <CODE>Transition</CODE> object with an
 * additional field for the label, which determines if the machine should move
 * on this transition.
 * 
 * @see automata.fsa.FiniteStateAutomaton
 * 
 * @author Thomas Finley
 */

public class FSATransition extends Transition<FSATransitionLabel> {
	/**
	 * Instantiates a new <CODE>FSATransition</CODE> object.
	 * 
	 * @param from
	 *            the state this transition comes from
	 * @param to
	 *            the state this transition goes to
	 * @param myLabel2
	 *            the label for this transition, roughly intended to be that
	 *            string that the current string in the machine should satisfy
	 *            before moving on to the next state
	 */
	public FSATransition(State from, State to, FSATransitionLabel s) {
		super(from, to, s);
	}
	
	public FSATransition(State from, State to) {
		super(from, to, new FSATransitionLabel());
	}
	
	public FSATransition(State from, State going, SymbolString subList) {
		this(from, going, new FSATransitionLabel(subList));
	}

	/**
	 * Returns the label for this transition.
	 */
	@Override
	public FSATransitionLabel getLabel() {
		return myLabel;
	}

	/**
	 * Sets the label for this transition.
	 * 
	 * @param myLabel2
	 *            the new label for this transition
	 * @throws IllegalArgumentException
	 *             if the label contains any "bad" characters, i.e., not
	 *             alphanumeric
	 */
	@Override
	public void setLabel(FSATransitionLabel label) {
		myLabel = label;
	}


	public void setLabel(SymbolString string) {
		this.setLabel(new FSATransitionLabel(string));
	}

	/**
	 * Returns if this transition equals another object.
	 * 
	 * @param object
	 *            the object to test against
	 * @return <CODE>true</CODE> if the two are equal, <CODE>false</CODE>
	 *         otherwise
	 */
	public boolean equals(Object object) {
		try {
			FSATransition t = (FSATransition) object;
			return super.equals(t) && myLabel.equals(t.myLabel);
		} catch (ClassCastException e) {
			return false;
		}
	}

	/**
	 * Returns the hash code for this transition.
	 * 
	 * @return the hash code for this transition
	 */
	public int hashCode() {
		return super.hashCode() ^ myLabel.hashCode();
	}

	/**
	 * The label for this transition, which is intended to be used as the
	 * precondition that a string must satisfy before the machine continues.
	 */
	protected FSATransitionLabel myLabel;
}
