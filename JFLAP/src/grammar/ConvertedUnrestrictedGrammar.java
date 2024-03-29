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
 * Special grammar for JFLAP to recognized that Grammar is "converted grammar from Turing Mahcine"
 * @author Kyung Min (Jason) Lee
 *
 */
public class ConvertedUnrestrictedGrammar extends Grammar {


	private boolean myTuringBool=false;
	/**
	 * Every production is all right except those with lambda in the left hand
	 * side of the production or if the first production added is unrestricted on the left
	 *             hand side
	 * 
	 * @param production
	 *            the production to check
	 * @return 
	 * @throws IllegalArgumentException
	 *             if the production is lambda on the left hand side
	 */
	public BooleanWrapper checkProduction(Production production) {
		if (production.getLHS().size() == 0) {
			return new BooleanWrapper(false, "The left hand side cannot be empty.");
		}
		if (myProductions.size() == 0
				&& !ProductionChecker.isRestrictedOnLHS(production))
			return new BooleanWrapper(false, 
					"The first production must be restricted on the Left-hand side.");
		
		return super.checkProduction(production);
	}
	
	@Override
	public boolean isConverted() {
		return false;
	}

	@Override
	public String getName() {
		return "Converted Unrestricted Grammar";
	}


}
