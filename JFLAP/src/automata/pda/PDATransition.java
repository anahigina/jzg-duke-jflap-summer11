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





package automata.pda;

import gui.environment.Universe;
import JFLAPnew.formaldef.symbols.SymbolString;
import automata.ITransitionLabel;
import automata.Transition;
import automata.State;

/**
 * A <CODE>PDATransition</CODE> is a <CODE>Transition</CODE> object with
 * additional fields for the label (input to read), the string to pop off the
 * stack, and the string to push on the stack.
 * 
 * @see automata.pda.PushdownAutomaton
 * 
 * @author Ryan Cavalcante
 */

public class PDATransition extends Transition<PDATransitionLabel> {
	
	public static final int INPUT = 0,
							POP = 1,
							PUSH = 2;
	private PDATransitionLabel myLabel;

	/**
	 * Instantiates a new <CODE>PDATransition</CODE> object.
	 * 
	 * @param from
	 *            the state this transition comes from
	 * @param to
	 *            the state this transition goes to
	 * @param symbolString
	 *            the string that the machine should satisfy before moving on to
	 *            the next state.
	 * @param pop
	 *            the string that the machine should pop from the stack.
	 * @param push
	 *            the string that the machine should push on to the stack.
	 */
	public PDATransition(State from, State to, SymbolString in,
			SymbolString pop, SymbolString push) {
		this(from, to, new PDATransitionLabel(in,pop,push));
	}
	
	public PDATransition(State from, State to, PDATransitionLabel label) {
		super(from, to, label);
	}

	/**
	 * Constructs a PDA transition with empty input/pop/push
	 * @param from
	 * @param to
	 */
	public PDATransition(State from, State to){
		this(from, to, new SymbolString(), 
						new SymbolString(), 
						new SymbolString());
	}

	/**
	 * Returns the input to read portion of the transition label for this
	 * transition.
	 */
	public SymbolString getInputToRead() {
		return myLabel.getInputToRead();
	}

	/**
	 * Sets the input to read portion of the transition label for this
	 * transition.
	 * 
	 * @param symbolString
	 *            the input to read portion of the transition label.
	 */
	protected void setInputToRead(SymbolString symbolString) {
		myLabel.setInput(symbolString);
	}

	/**
	 * Returns the string to pop from stack portion of the transition label for
	 * this transition.
	 */
	public SymbolString getStringToPop() {
		return myLabel.getPop();
	}

	/**
	 * Sets the string to pop from stack portion of the transition label for
	 * this transition.
	 * 
	 * @param pop
	 *            the string to pop from the stack.
	 */
	protected void setStringToPop(SymbolString pop) {
		myLabel.setPop(pop);
	}

	/**
	 * Returns the string to push on to the stack portion of the transition
	 * label for this transition.
	 */
	public SymbolString getStringToPush() {
		return myLabel.getPush();

	}

	/**
	 * Sets the string to push on to the stack portion of the transition label
	 * for this transition.
	 * 
	 * @param push
	 *            the string to push on to the stack.
	 */
	protected void setStringToPush(SymbolString push) {
		myLabel.setPush(push);
	}

	/**
	 * Returns a string representation of this object. This is the same as the
	 * string representation for a regular transition object, with the
	 * additional fields tacked on.
	 * 
	 * @see automata.Transition#toString
	 * @return a string representation of this object
	 */
	public String toString() {
		return super.toString() + ": \"" + this.getLabel().toString();
	}


	@Override
	public PDATransitionLabel getLabel() {
		return myLabel;
	}

	@Override
	public void setLabel(PDATransitionLabel label) {
		myLabel = label;
	}

	public void setLabelComponent(int c, SymbolString string) {
		switch (c){
			case INPUT: this.setInputToRead(string); break;
			case POP: this.setStringToPop(string); break;
			case PUSH: this.setStringToPush(string); break;
		}
	}
	
	public SymbolString getByIndex(int i){
		switch (i){
			case INPUT: return this.getInputToRead();
			case POP: return this.getStringToPop(); 
			case PUSH: return this.getStringToPush(); 
		}
		return null;
	}

}
