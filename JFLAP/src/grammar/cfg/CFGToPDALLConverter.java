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





package grammar.cfg;

import java.util.Set;

import grammar.Grammar;
import grammar.GrammarToAutomatonConverter;
import grammar.Production;
import JFLAPnew.formaldef.symbols.Symbol;
import JFLAPnew.formaldef.symbols.SymbolString;
import JFLAPnew.formaldef.symbols.terminal.Terminal;
import JFLAPnew.formaldef.symbols.variable.Variable;
import automata.Automaton;
import automata.State;
import automata.StatePlacer;
import automata.Transition;
import automata.pda.PDATransition;
import automata.pda.PushdownAutomaton;

/**
 * The CFG to PDA (LL parsing) converter can be used to convert a context free
 * grammar to a pushdown automaton that can be used for LL parsing. You can do
 * the conversion simply by calling convertToAutomaton, or you can do the
 * conversion step by step by first calling createStatesForConversion, which
 * will create all the states in the pushdown automaton necessary for the
 * conversion, and then calling getTransitionForProduction for each production
 * in the grammar. You must of course add each Transition returned by this call
 * to your pushdown automaton. When you have done this for each production in
 * your grammar, the equivalent PDA will be complete.
 * 
 * @author Ryan Cavalcante
 */

public class CFGToPDALLConverter extends GrammarToAutomatonConverter {
	/**
	 * Creates an instance of <CODE>CFGToPDALLConverter</CODE>.
	 */
	public CFGToPDALLConverter() {

	}

	/**
	 * Returns the transition created by converting <CODE>production</CODE> to
	 * its equivalent transition.
	 * 
	 * @param production
	 *            the production
	 * @return the equivalent transition.
	 */
	public Transition getTransitionForProduction(Production production) {
		SymbolString lhs = production.getLHS();
		SymbolString rhs = production.getRHS();
		Transition transition = new PDATransition(INTERMEDIATE_STATE,
				INTERMEDIATE_STATE, EMPTY, lhs, rhs);
		return transition;
	}

	/**
	 * Adds all states to <CODE>automaton</CODE> necessary for the conversion
	 * of <CODE>grammar</CODE> to its equivalent automaton. This creates three
	 * states--an initial state, an intermediate state, and a final state. It
	 * also adds transitions connecting the three states, and transitions for
	 * each terminal in <CODE>grammar</CODE>
	 * 
	 * @param grammar
	 *            the grammar being converted.
	 * @param automaton
	 *            the automaton being created.
	 */
	public void createStatesForConversion(Grammar grammar, Automaton automaton) {
		initialize();
		StatePlacer sp = new StatePlacer();
		Symbol BOTTOM_OF_STACK = ((PushdownAutomaton)automaton).getBottomOfStackSymbol();
		State initialState = automaton.createState(sp
				.getPointForState(automaton));
		automaton.setInitialState(initialState);

		State intermediateState = automaton.createState(sp
				.getPointForState(automaton));
		INTERMEDIATE_STATE = intermediateState;

		State finalState = automaton
				.createState(sp.getPointForState(automaton));
		automaton.addFinalState(finalState);

		SymbolString temp = new SymbolString(grammar.getStartVariable());
		temp.concat(BOTTOM_OF_STACK);
		PDATransition trans1 = new PDATransition(initialState,
				intermediateState, EMPTY, new SymbolString(BOTTOM_OF_STACK), temp);
		automaton.addTransition(trans1);
		PDATransition trans2 = new PDATransition(intermediateState, finalState,
				EMPTY, new SymbolString(BOTTOM_OF_STACK), EMPTY);
		automaton.addTransition(trans2);

		Set<Terminal> terminals = grammar.getTerminalAlphabet().getSymbols();
		for (Terminal t: terminals) {
			automaton.addTransition(new PDATransition(intermediateState,
														intermediateState, 
														new SymbolString(t), 
														new SymbolString(t), 
														new SymbolString(t)));
		}

	}

	/** the intermediate state in the automaton. */
	protected State INTERMEDIATE_STATE;
}
