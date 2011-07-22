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

import gui.errors.BooleanWrapper;

/**
 * The unrestricted grammar is a grammar that can have any production added to
 * it, save for the initial production, which must be restricted since the first
 * production specifies the initial variable in JFLAP.
 * 
 * @author Thomas Finley
 */

public class UnrestrictedGrammar extends Grammar {

	private boolean myTuringBool=false;
	/**
	 * Every production is all right except those with lambda in the left hand
	 * side of the production.
	 * 
	 * @param production
	 *            the production to check
	 * @return 
	 * @throws IllegalArgumentException
	 *             if the production is lambda on the left hand side
	 */
	public BooleanWrapper checkProduction(Production production) {
		if (production.getLHS().size() == 0) {
			return new BooleanWrapper(false,
					"The left hand side cannot be empty.");
		}
		if (myProductions.size() == 0
				&& !ProductionChecker.isRestrictedOnLHS(production))
			return new BooleanWrapper(false,
					"The first production must be restricted.");
		return super.checkProduction(production);
		
	}

	@Override
	public boolean isConverted() {
		return false;
	}
}
