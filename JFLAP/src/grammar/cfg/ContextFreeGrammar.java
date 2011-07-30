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

import javax.swing.JOptionPane;

import grammar.*;
import gui.errors.BooleanWrapper;
import gui.grammar.automata.GrammarCreationException;

/**
 * The context free grammar object is a representation of a context free
 * grammar. This object is a data structure of sorts, maintaining the data
 * pertinent to the definition of a context free grammar.
 * 
 * @author Ryan Cavalcante
 */

public class ContextFreeGrammar extends UnboundGrammar {
	/**
	 * Creates an instance of <CODE>ContextFreeGrammar</CODE>. The created
	 * instance has no productions, no terminals, no variables, and specifically
	 * no start variable.
	 */
	public ContextFreeGrammar() {
		super();
	}

	/**
	 * Throws an exception if the production is unrestricted on the left hand
	 * side.
	 * 
	 * @param production
	 *            the production to check
	 * @return Boolean wrapper false
	 *             if the production is unrestricted on the left hand side
	 */
	public BooleanWrapper checkProduction(Production production) {
		return new BooleanWrapper(!ProductionChecker.isRestrictedOnLHS(production),
					"The production is unrestricted on the left hand side.");
	}

	@Override
	public boolean isConverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return "Context Free Grammar";
	}

}
