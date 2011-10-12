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





package automata.mealy;

import gui.environment.Universe;
import JFLAPnew.formaldef.symbols.SymbolString;
import automata.State;
import automata.Transition;

/**
 * A <code>MealyTransition</code> is a <code>Transition</code>
 * object with additional fields for the label and output. The
 * label determines if the machine should move on this transition
 * and the output is the output produced  if the machine moves
 * on this transition.
 * 
 * @see MealyMachine
 * @author Jinghui Lim
 *
 */
public class MealyTransition extends Transition<MealyTransitionLabel>
{
    /**
     * Transition label
     */
    protected MealyTransitionLabel myLabel;
    
    public static final int INPUT = 0,
    		                OUTPUT = 1;
    
    /**
     * Instantiates a new <code>MealyTransition</code> object.
     * 
     * @param from the state this transition comes from
     * @param to the state this transition goes to
     * @param label the label for this transition that the input string
     * in the machine should match before moving through this transition
     * @param output the output this transition produces
     */
    public MealyTransition(State from, State to, SymbolString read, SymbolString output) 
    {
        this(from, to, new MealyTransitionLabel(read, output));
    }
    
    public MealyTransition(State from, State to, MealyTransitionLabel label) {
		super(from, to, label);
	}

	/**
     * Produces a copy of this transition with new from and to states.
     * 
     * @param from the new from state
     * @param to the new to state
     * @return a copy of this transition with new states
     */
    public Transition copy(State from, State to) 
    {
        return new MealyTransition(from, to, (MealyTransitionLabel) myLabel.clone());
    }
    
    
    /**
     * Returns the label for this transition.
     * 
     * @return the label for this transition
     */
    @Override
    public MealyTransitionLabel getLabel()
    {
        return myLabel;
    }
    
    
    
    /**
     * Returns a string representation of this object. This is the same
     * as the string representation for a regular transition object with
     * the label and output tacked on.
     * 
     * @see automata.Transition#toString()
     * @return a string representation of this object
     */
    public String toString()
    {
        return super.toString() + ": \"" + getInput() + "/" + getOutput() + "\"";
    }
    
    public SymbolString getOutput() {
		return this.getLabel().getOutput();
	}
    
    public SymbolString getInput() {
		return this.getLabel().getInput();
	}

	
	@Override
	public void setLabel(MealyTransitionLabel label) {
		myLabel = label;
	}
}
